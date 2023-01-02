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

  // start folders ///////////////////////
  createFolder(info: string): Observable<string[]>{
    return this.http.post<string[]>("http://localhost:9090/createFolder", info);
  }

  getFolders(activeUser: string): Observable<string[]>{
    return this.http.post<string[]>("http://localhost:9090/getFolders", activeUser);
  }

  deleteFolder(info: string): Observable<string[]>{
    return this.http.post<string[]>("http://localhost:9090/deleteFolder", info);
  }

  renameFolder(info: string): Observable<string[]>{
    return this.http.post<string[]>("http://localhost:9090/renameFolder", info);
  }

  // end folder ///////////////////////////

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
    return this.http.post<IMessage[]>("http://localhost:9090/MovetoFolder", moved);
  }

  ////////////sorting////////////////

  sortRequest(info: any): Observable<IMessage[]>{
    return this.http.post<IMessage[]>("http://localhost:9090/Sort", info);
  }

  searchRequest(info: any): Observable<IMessage[]>{
    return this.http.post<IMessage[]>("http://localhost:9090/Search", info);
  }

  filterRequest(info: any): Observable<IMessage[]>{
    return this.http.post<IMessage[]>("http://localhost:9090/Filter", info);
  }

  /////////////////////////////////
  priorityRequest(UserName:any){
    return this.http.post<IMessage[]>("http://localhost:9090/SortInboxByPriority",UserName);
  }
}
