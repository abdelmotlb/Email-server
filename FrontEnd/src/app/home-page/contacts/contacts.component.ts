import { Component, OnInit } from '@angular/core';
import { LoginComponent } from 'src/app/login/login.component';
import { ContactsService } from './contacts.service';

export interface IContact{
  username:string
  emails:string[]
}
export class Contact implements IContact{
  username: string;
  emails:string[];

  //empty constructor
  constructor(username:string, emails:string[]){
    this.username=username
    this.emails=emails
  }
}


var contact:IContact 

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent {

  constructor(private cnt:ContactsService) { }

    add(){
    console.log("IN ADD")
    var username = "test4";
    var emails = ["test5", "test6"]
    var contact:IContact = new Contact(username, emails)
    this.cnt.addContact(contact).subscribe((res:any) => {alert(res)})
  }

}
