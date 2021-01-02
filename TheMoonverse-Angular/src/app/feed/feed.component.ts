import { Component, OnInit } from '@angular/core';
import {FeedService } from '../feed.service';
import { Posts } from '../posts';


@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})

export class FeedComponent implements OnInit {
  post: Posts;
  posts:Posts[];
  constructor(private feed: FeedService) {}

  ngOnInit(): void {
    this.getFeed();
  }
  

    // onSelect(thispost: posts): void {
  //   this.thisPost = thispost;
  // }
  
  //may overload this later so this service can be used to populate the 
  //profile user feed 

  getFeed(): void {
    //this.feed= this.feedservice.getfeed
    this.feed.getFeed()
        .subscribe(posts => this.posts = posts);
  }

  like(likes: number): void{
    
    
  }

}
