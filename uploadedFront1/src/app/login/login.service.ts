import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  signUp(userData: string){
    return this.http.post("http://localhost:9090/signUp", userData);
  }

  logIn(userData: string){
    return this.http.post("http://localhost:9090/logIn", userData);
  }
  
  

}
