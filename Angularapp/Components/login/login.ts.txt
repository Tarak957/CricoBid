import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private authService: AuthService,private router : Router) {}

  ngOnInit(): void {}

  login(form : NgForm) {
    if(form.valid) {
      console.log("Form is valid")
      this.authService.loginUser(form.value).subscribe(
        (data)=>{
          alert("Login Successful!")

          localStorage.setItem("id",data.userId+'')
          localStorage.setItem("role",data.role);
          
          if(data.role==="ADMIN"){
            this.router.navigate(['/admin'])
          }
          if(data.role==="ORGANIZER"){
            this.router.navigate(['/organizer'])
          }
          
      },(error)=>{
          console.log(form.value)
          alert("User not Registered!!");
          console.log("Error is login failed"+JSON.stringify(error));
      })
    }
    else {
      alert("Invalid Form Input!!");
    }
  }
}
 
