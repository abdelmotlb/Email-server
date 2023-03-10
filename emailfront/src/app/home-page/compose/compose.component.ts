import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/login/login.component';
import { HomeService } from '../home.service';



@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {

  reciver: any = "";
  subject: any = "";
  message: any = "";
  priorty: any = "1";
  attachments: File[] = [];
  filesNames: string[] = [];
  filesSize: number = 0;
  thereAttach: string = "no";
  formisvalid: boolean = false;

  setReciver(to: string) {
    this.reciver = to;
  }
  setSubject(sub: string) {
    this.subject = sub;
    if( this.subject == "" ){
      this.subject = "(empty)";
    }
  }
  setMessage(mes: string) {
    this.message = mes;
  }
  constructor(private dialogRef: MatDialogRef<ComposeComponent>, private serviceObj: HomeService) { }

  ngOnInit(): void {
    // console.log(this.data);
    // console.log(this.data[0].name);
  }

  sendMessage(toTrash: any) {

    var valid = true;
    const formData = new FormData();

    // prepare files in form data
    if (this.attachments.length != 0) {
      for (const file of this.attachments) {
        formData.append('file', file);
        this.filesSize += file.size;
      }
      console.log(formData);
      console.log(this.filesSize);
      if (this.filesSize > 1048576) {
        valid = false;
        this.filesSize = 0;
      }
    }

    if (valid) {
      if (this.attachments.length != 0) {
        this.thereAttach = "yes";
        //all message parts except attachment request
        console.log(this.reciver + " " + this.subject + " " + this.message + " " + this.priorty)
        this.serviceObj.composeMessage(LoginComponent.activeUser + "$" + this.reciver + "$" + this.subject + "$" + this.message + "$" + this.priorty + "$" + toTrash + "$" + this.thereAttach).subscribe((res: any) => {
          // console.log("compose " + res);
        });
        //attachment request
        this.serviceObj.sendAttachReq(formData).subscribe((res: any) => {
          alert(res);
        });
      } else {
        //all message parts except attachment request
        console.log(this.reciver + " " + this.subject + " " + this.message)
        this.serviceObj.composeMessage(LoginComponent.activeUser + "$" + this.reciver + "$" + this.subject + "$" + this.message + "$" + this.priorty + "$" + toTrash + "$" + this.thereAttach).subscribe((res: any) => {
          alert(res);
        });
      }

    }
    else
      alert("over size")
  }

  removeAttach(name: any) {
    console.log(name);
    this.attachments.splice(this.filesNames.indexOf(name), 1);
    this.filesNames.splice(this.filesNames.indexOf(name), 1);
    console.log(this.attachments)
  }

  sendAttach(files: any) {

    for (const file of files) {
      if (!this.filesNames.includes(file.name)) {
        this.filesNames.push(file.name);
        this.attachments.push(file);
      }
    }
    console.log(this.attachments)
  }

  setPriorty(num: any) {
    this.priorty = num;
  }



}