import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Posts } from './posts';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  private post: Posts;

  private feedUrl= 'http://localhost:9090/api/v3'; /*
    Define the heroesUrl of the form :base/:collectionName with the address of the posts resource on the server. 
    Here base is the resource to which requests are made, and collectionName is the posts data object 
    in the in-memory-data-service.ts.*/
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient){}
  //we could put some message here but I left that out bc it did't seeem useful

  //if possible add a mechanic to load ten posts at at time and load more on click

  getFeed(): Observable<Posts[]> {
    const url = `${this.feedUrl}/posts`;
    return this.http.get<Posts[]>(url)
  } 

  getPost(id:number): Observable<Posts>{
    const url = `${this.feedUrl}/posts/${id}`;
    return this.http.get<Posts>(url);
  }

  getFeedById(id:number): Observable<Posts[]>{
    const url = `${this.feedUrl}/myposts/${id}`;
    return this.http.get<Posts[]>(url);
  }

  makePost(post: Posts): Observable<Posts>{
    return this.http.post<Posts>(`${this.feedUrl}/addpost`, post, this.httpOptions);
  }
  
  updatePost(post: Posts): Observable<Posts>{
    return this.http.put<Posts>(this.feedUrl, post, this.httpOptions);
  }
}
