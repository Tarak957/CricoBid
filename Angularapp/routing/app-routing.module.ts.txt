import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../components/home/home.component';
import { LoginComponent } from '../components/login/login.component';
import { RegistrationComponent } from '../components/registration/registration.component';
import { AdminComponent } from '../components/admin/admin.component';
import { ErrorComponent } from '../components/error/error.component';
import { OrganizerComponent } from '../components/organizer/organizer.component';
import { AdminGuard } from '../guards/admin.guard';
import { OrganizerGuard } from '../guards/organizer.guard';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegistrationComponent},
  {path:'admin',component:AdminComponent,canActivate:[AdminGuard]},
  {path:'organizer',component:OrganizerComponent,canActivate:[OrganizerGuard]},
  {path:'error',component:ErrorComponent},
  {path:"**",redirectTo:"error",pathMatch:'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
