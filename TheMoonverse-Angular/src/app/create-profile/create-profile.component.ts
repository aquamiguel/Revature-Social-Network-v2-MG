import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Profile } from '../profile';
import { ProfileService } from '../profile.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.css']
})
export class CreateProfileComponent implements OnInit {
  user: User = new User();
  profile: Profile = new Profile();
  userId: any;
  
  
  constructor(private userService: UserService, private profileService: ProfileService, private _route: ActivatedRoute, private _router: Router, private cookieService: CookieService) {
    
    }
  ngOnInit() {
    this.userId = this.cookieService.get('userId');
    this.userService.getUser(this.userId).subscribe(
      data => 
      {
       console.log(data)
      this.user = data;
      }
      )
  }

onSubmit(){
      this.profile.profileId = 0;
      this.profile.profilePicture = "https://moonv.s3.us-east-2.amazonaws.com/moon.jpg";
      this.profile.user = this.user;
      this.profileService.createProfile(this.profile).subscribe(
    data2 => {
      
      console.log(data2);
      this.profile = new Profile();
      this._router.navigate(['profile']);
    }
  )
}
}