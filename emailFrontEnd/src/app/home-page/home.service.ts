import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }
  composeMessage(userData: any){
    return this.http.post("http://localhost:9090/Compose", userData, {responseType:'text'});
  }
}
