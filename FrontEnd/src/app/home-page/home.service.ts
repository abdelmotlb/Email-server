import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  addContact(contact: object) {
    return this.http.post("http//localhost:9090/addContact", contact);
  }

  signOut() {
    return this.http.post("http://localhost:9090/signOut", "signOut");
  }

  composeMessage(userData: any) {
    return this.http.post("http://localhost:9090/Compose", userData, { responseType: 'text' });
  }

  sendAttachReq(file: FormData) {
    console.log("in req")
    console.log(file);
    return this.http.post("http://localhost:9090/Attach", file, { responseType: 'text' });

  }
}
