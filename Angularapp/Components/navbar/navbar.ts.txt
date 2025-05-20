import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(private router:Router,public authService:AuthService) { }

  ngOnInit(): void {
  }

  logout(){
    if(confirm("Are you sure you want to logout?")){
      this.authService.loggedOut()
      this.router.navigate(['/login'])
    }
  }
}
