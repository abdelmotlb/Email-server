import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login.component';
import { IContact } from './contacts.component';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

   constructor(private http:HttpClient) { }

   USERNAME = LoginComponent.USERNAME 


   addContact(contact : IContact) :Observable<String> { 
    return this.http.post("http://localhost:9090/add?username=" + "Ahmed", contact, {responseType:'text'});
   }
 

}
