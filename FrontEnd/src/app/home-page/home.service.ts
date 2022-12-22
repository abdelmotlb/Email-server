import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http:HttpClient) { }

  addContact(contact : object){
    return this.http.post("http//localhost:9090/addContact", contact);
  }
}
