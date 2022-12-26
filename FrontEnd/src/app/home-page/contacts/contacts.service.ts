import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login.component';
import { Contact, IContact } from './contacts.component';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

   constructor(private http:HttpClient) { }

   USERNAME = LoginComponent.USERNAME 


   addContact(email : string , username : string) :Observable<String> { 
    return this.http.post("http://localhost:9090/add?username=" + username , email, {responseType:'text'});
   }

   addEmail(email: string, username : string) :Observable<String> {
    return this.http.post("http://localhost:9090/addEmail?username=" + username , email, {responseType:'text'})
   }

   display() :Observable<IContact[]> {
    console.log(this.USERNAME)
    return this.http.post<IContact[]>("http://localhost:9090/display","Ahmed")
   }

   toEdit(username : string) :Observable<IContact[]>{
       return this.http.post<IContact[]>("http://localhost:9090/edit", username )
   }

   cancelEdit(toBeEdited : string) :Observable<IContact[]>{
       return this.http.post<IContact[]>("http://localhost:9090/cancelEdit", toBeEdited)
   }

   update(obj : string[], username:string) :Observable<IContact[]>{
       return this.http.post<IContact[]>("http://localhost:9090/update?username=" + username, obj)
   }

   delete(username : string) {
       return this.http.post("http://localhost:9090/delete" , username)
   }

   get(){
      return this.http.get<IContact[]>("http://localhost:9090/get")
   }
 

}
