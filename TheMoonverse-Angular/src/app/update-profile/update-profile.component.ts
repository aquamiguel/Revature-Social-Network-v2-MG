import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Profile } from '../profile';
import { ProfileService } from '../profile.service';
import { User } from '../user';
import { UserService } from '../user.service';


@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  profile: Profile = new Profile();
  user: User = new User();
  userId: any;
  firstName: string;
  lastName: string;
  profileId: any;
  

  constructor(private userService: UserService, private profileService: ProfileService, private _route: ActivatedRoute, private _router: Router, private cookieService: CookieService) { }

    ngOnInit() {
      this.userId = this.cookieService.get('userId');
      this.firstName = this.cookieService.get('firstName');
      this.lastName = this.cookieService.get('lastName');

      this.userService.getUser(this.userId).subscribe(
        data => 
        {
         console.log(data)
        this.user = data;
        }
        )

        this.profileService.getProfile(this.userId).subscribe(
          data2 => {
          console.log(data2);
          this.profile = data2;
          this.cookieService.set('profileId', `${this.profile.profileId}`); 
        }
        )
    }
  
  onSubmit(){
        this.profileId = this.cookieService.get('profileId');
        this.profile.profilePicture = "https://moonv.s3.us-east-2.amazonaws.com/moon.jpg";
        this.profile.user = this.user;
        console.log(this.profile);
        this.profileService.updateProfile(this.profile).subscribe(
        data3 => {
        console.log(data3);
        this._router.navigate(['profile']);
      }
    )
  }
}
