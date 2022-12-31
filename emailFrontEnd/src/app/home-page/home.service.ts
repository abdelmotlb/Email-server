import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginComponent } from '../login/login.component';
import { IMessage } from './home-page.component';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  // addContact(contact: object, activeUser: any) {
  //   return this.http.post("http//localhost:9090/addContact", contact, activeUser);
  // }

  // signOut() {
  //   return this.http.post("http://localhost:9090/signOut", "signOut");
  // }

  composeMessage(userData: any) {
    return this.http.post("http://localhost:9090/Compose", userData, { responseType: 'text' });
  }

  sendAttachReq(file: FormData) {
    console.log("in req")
    console.log(file);
    return this.http.post("http://localhost:9090/Attach", file, { responseType: 'text' });
  }

  getMessages(file: string): Observable<IMessage[]> {
    return this.http.post<IMessage[]>("http://localhost:9090/getMessages", LoginComponent.activeUser + "$" + file);
  }
}
