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
  username = ""
  email = ""
  temp:any
  dis:any
  
  constructor(private cnt:ContactsService) { }

    add(){
    document.getElementById("addForm")!.style.display = "block"
     
  }

  addContactForm(){
    this.username = (<HTMLInputElement>document.getElementById("username")).value;
     this.email = (<HTMLInputElement>document.getElementById("email")).value;
     console.log(this.username)
    this.cnt.addContact(this.email, this.username).subscribe((res:any) => {alert(res)})
  }

  addEmailForm(){
     this.username = (<HTMLInputElement>document.getElementById("username")).value;
     this.email = (<HTMLInputElement>document.getElementById("email")).value;
     this.cnt.addEmail(this.email, this.username).subscribe((res:any) => {alert(res)})
  }

  display(){
    this.cnt.display().subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)});
  }
  print(test:any){
    console.log(this.dis)
  }

}
