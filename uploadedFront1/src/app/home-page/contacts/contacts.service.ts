import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login.component';
import { Contact, IContact } from './contacts.component';

@Injectable({
    providedIn: 'root'
})
export class ContactsService {

    constructor(private http: HttpClient) { }

    // USERNAME = LoginComponent.activeUser


    addContact(email: string, username: string): Observable<String> {
        return this.http.post("http://localhost:9090/add?username=" + username + "$" + LoginComponent.activeUser, email, { responseType: 'text' });
    }

    addEmail(email: string, username: string): Observable<String> {
        return this.http.post("http://localhost:9090/addEmail?username=" + username, email + "$" + LoginComponent.activeUser, { responseType: 'text' })
    }

    display(): Observable<IContact[]> {
        // console.log(this.USERNAME)
        return this.http.post<IContact[]>("http://localhost:9090/display", LoginComponent.activeUser)
    }

    toEdit(username: string): Observable<IContact[]> {
        return this.http.post<IContact[]>("http://localhost:9090/edit", username + "$" + LoginComponent.activeUser)
    }

    cancelEdit(toBeEdited: string): Observable<IContact[]> {
        return this.http.post<IContact[]>("http://localhost:9090/cancelEdit", toBeEdited + "$" + LoginComponent.activeUser)
    }

    update(obj: string[], username: string): Observable<IContact[]> {
        return this.http.post<IContact[]>("http://localhost:9090/update?username=" + username + "$" + LoginComponent.activeUser, obj)
    }

    delete(username: string) {
        return this.http.post("http://localhost:9090/delete", username + "$" + LoginComponent.activeUser)
    }

    // get() {
    //     return this.http.get<IContact[]>("http://localhost:9090/get")
    // }
    get(): Observable<IContact[]> {
        return this.http.post<IContact[]>("http://localhost:9090/delete", LoginComponent.activeUser)
    }


}
