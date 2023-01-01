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

  // attachments
  download(fileName: string){
    console.log(fileName + "in service");
    return this.http.post("http://localhost:9090/downloadAttach", fileName, { responseType: 'text' });
  }

  // operation on selected messages.
  deleteSelected(deleted: string): Observable<IMessage[]> {
    console.log(deleted + "in service");
    return this.http.post<IMessage[]>("http://localhost:9090/deleteSelected", deleted);
  }

  // operation on selected messages.
  moveSelected(moved: string): Observable<IMessage[]> {
    console.log(moved + "in service");
    return this.http.post<IMessage[]>("http://localhost:9090/moveSelected", moved);
  }

}
