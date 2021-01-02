import { Component, OnInit } from '@angular/core';
import { FeedService } from '../feed.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Posts } from '../posts';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  //creates variables of type Posts and type Posts Array
  post: Posts = new Posts();
  posts: Posts[];
  dateMessage: String;
  submitted = false;
  user: User = new User;
  userId: any;




  constructor(
    private route: ActivatedRoute,
    private feed: FeedService,
    private router: Router,
    private cookie: CookieService,
    private userService: UserService
  ){
    
  }
  ngOnInit() {
    let currentDate = new Date()
    this.dateMessage = currentDate.toDateString();
    this.userId = this.cookie.get('userId');
    this.userService.getUser(this.userId).subscribe(
      data => {
        this.user = data;
      }
    )
    
  }

  onSubmit(){
   this.post.postId = 0;
   this.post.user = this.user;
   this.post.countLikes=0;
   this.post.postDate = '2020-12-28';
   this.post.postMediaUrl = 'https://moonv.s3.us-east-2.amazonaws.com/rocket.jpeg';

   this.feed.makePost(this.post)
   .subscribe(post => {
     console.log(post)
     this.post = new Posts();
     this.router.navigate(['feed']);
   });
  }

  
}
