
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProfileComponent } from './profile/profile.component';
import { PostsComponent } from './posts/posts.component';
import{ FeedComponent } from './feed/feed.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ResetComponent } from './reset/reset.component';
import { AuthGuard } from './auth-guard.service';
import { CreateProfileComponent } from './create-profile/create-profile.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  { path: 'posts',  component: PostsComponent, canActivate: [AuthGuard]},
  { path: 'feed', component: FeedComponent, canActivate: [AuthGuard]},  
  { path: 'register', component: RegisterComponent},
  { path: 'update-profile', component: UpdateProfileComponent, canActivate: [AuthGuard]},
  { path: 'create-profile', component: CreateProfileComponent, canActivate: [AuthGuard]},
  { path: 'reset', component: ResetComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }