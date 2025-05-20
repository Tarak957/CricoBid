import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from 'src/models/user.model';
 
 
@Injectable({
  providedIn: 'root'
})
 
export class AuthService {

  private apiUrl:string='https://8080-eebaaeeaec326817061edeccfaaefdtwo.premiumproject.examly.io/api';
  constructor(private http : HttpClient) { }

  loginUser(user : User) : Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user/login`,user)
  }

  registerUser(user : User) : Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user/register`,user)
  }

  isAdmin():boolean {
    return localStorage.getItem('role') === 'ADMIN'
  }

  isOrganizer():boolean {
    return localStorage.getItem('role') === 'ORGANIZER'
  }

  isLoggedUser():boolean{
    return !!localStorage.getItem('id')
  }

  loggedOut() : void {
    localStorage.removeItem('id')
    localStorage.removeItem('role');
  }
}
 
 

