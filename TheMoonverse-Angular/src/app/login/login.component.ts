import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  title = 'The MoonVerse';
  email: string;
  password: string;
  user: User = new User();
  public loginInvalid: boolean;

  constructor(private myUser:UserService, private _route: ActivatedRoute, private _router: Router, private cookieService: CookieService, private authService: AuthService) { }

ngOnInit() {
  this.cookieService.deleteAll();
  this.authService.logout();
}
onSubmit() {
  this.loginInvalid = false;
  try {
    this.myUser.getLogin(this.user.email, this.user.password).subscribe(
      data => {
        console.log(data);
        this.user = data;
        console.log(this.user.userId);
        this.cookieService.set('userId', `${this.user.userId}`);
        this.cookieService.set('firstName', `${this.user.firstName}`)
        this.cookieService.set('lastName', `${this.user.lastName}`)
        console.log(this.cookieService.get('userId'));
        console.log(this.cookieService.get('firstName'));
        console.log(this.cookieService.get('lastName'));
        this._router.navigate(['profile']);
      } 

    )

  } catch (err) {
    this.loginInvalid = true;
  }
}

}
