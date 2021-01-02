import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Profile } from '../profile';
import { CookieService } from 'ngx-cookie-service';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})


  export class ProfileComponent implements OnInit 
  {
    profile: Profile = new Profile;
    userId: any;
    firstName: string;
    lastName: string;
    public userExists: boolean;
    public noProfile: boolean;

        constructor(private profileService: ProfileService, private _route: ActivatedRoute, private _router: Router, private cookieService: CookieService) { 
      }

      ngOnInit() { 
        this.userId = this.cookieService.get('userId');
        this.firstName = this.cookieService.get('firstName');
        this.lastName = this.cookieService.get('lastName');
        try{
          this.profileService.getProfile(this.userId).subscribe(
          data => {
          console.log(data);
          this.profile = data;
          this.cookieService.set('profileId', `${this.profile.profileId}`);
          this.userExists = true;
          this.noProfile = false;
          console.log(this.userExists);
        }
        )
      } finally {
        if(this.cookieService.get('profileId') == ("") || this.cookieService.get('profileId') == (" ") || this.cookieService.get('profileId') == ("undefined") ) {
          this.noProfile = true;
          console.log(this.noProfile);
        }
        
       
      }
    }
  }
