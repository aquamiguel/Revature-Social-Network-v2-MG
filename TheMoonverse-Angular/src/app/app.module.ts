
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './profile/profile.component';
import { PostsComponent } from './posts/posts.component';
import { FeedComponent } from './feed/feed.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ResetComponent } from './reset/reset.component';

import { CookieService } from 'ngx-cookie-service';
import { MatButtonModule,MatInputModule,MatCardModule} from '@angular/material';
import { ReversePipe } from './reverse.pipe';

import { AuthGuard } from './auth-guard.service';
import { CreateProfileComponent } from './create-profile/create-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    PostsComponent,
    PostsComponent,
    FeedComponent,
    LoginComponent,
    RegisterComponent,
    UpdateProfileComponent,
    ResetComponent,
    ReversePipe,
    CreateProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule
  ],
  providers: [CookieService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }