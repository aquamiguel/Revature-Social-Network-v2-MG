import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  
  private baseUrl= 'http://localhost:9090/api/v1'
  
  constructor(private http: HttpClient, private _authService: AuthService) { }
  getLogin(email:string, password:string):Observable<any>{
    console.log("in service login " + email + password)
    this._authService.login();
    return this.http.post(`${this.baseUrl}/verify`, {email, password}) ;
  }

  getUser(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/users/${userId}`);
  }

  registerUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/adduser`, user);
  }

  updatePassword(email: string, birthDate: string, password: string): Observable<Object> {
    return this.http.post(`${this.baseUrl}/reset`, {email, birthDate, password}) ;
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getUsers(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}