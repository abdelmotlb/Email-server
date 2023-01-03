import { Component, OnInit } from '@angular/core';
import { LoginComponent } from 'src/app/login/login.component';
import { ContactsService } from './contacts.service';
import { NgModel } from '@angular/forms';


export interface IContact{
  username:string
  emails:string[]
  edit:boolean
}
export class Contact implements IContact{
  username: string;
  emails:string[];
  edit:boolean;
  //empty constructor
  constructor(username:string, emails:string[], edit:boolean){
    this.username=username
    this.emails=emails
    this.edit=edit;
  }
}





@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  username = ""
  email = ""
  temp:any
  dis:any
  editFlag = false
  searchValue:any
  

  
  constructor(private cnt:ContactsService) { }

    add(){
    document.getElementById("addForm")!.style.display = "block"
     
  }

  ngOnInit() :void{
    this.display()
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


  //gets username of contact to be edited
  //send to back to change its flag to true
  Edit(username : string){
      this.cnt.toEdit(username).subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)})
  }

  cancelEdit(toBeEdited : string){
    this.cnt.cancelEdit(toBeEdited).subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)})

  }

  confirmEdit(oldUser : string){
    var obj:string[]
    var user:string
    user = (<HTMLInputElement>document.getElementById("username2")).value;
    user = user + "$" + oldUser
    var temp = (<HTMLInputElement>document.getElementById("emails2")).value;
    obj = temp.split(",")
    console.log(obj + user)
    this.cnt.update(obj, user).subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)})
    this.display()
  }

  delete(username : string){
    console.log(username)
    this.cnt.delete(username).subscribe()
    //this.cnt.display().subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)});
    
  }

  close(){
    document.getElementById("addForm")!.style.display = "none"
  }

  sortA(){
    this.cnt.sort("A").subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)})
  }

  sortD(){
    this.cnt.sort("D").subscribe((res : IContact[]) => {this.dis = res, this.print(this.dis)})
  }
  searchh(){
    this.cnt.search(this.searchValue).subscribe((res : any) => {this.dis = res, this.print(this.dis)})
  }

}