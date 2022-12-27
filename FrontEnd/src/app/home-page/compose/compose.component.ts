import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
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
  attachments: File[] = [];
  filesNames: string[] = [];
  filesSize: number = 0;
  thereAttach: string = "no";

  setReciver(to: string) {
    this.reciver = to;
  }
  setSubject(sub: string) {
    this.subject = sub;
  }
  setMessage(mes: string) {
    this.message = mes;
  }
  constructor(private dialogRef: MatDialogRef<ComposeComponent>, private serviceObj: HomeService) { }

  ngOnInit(): void {
  }

  sendMessage() {

    var valid = true;
    const formData = new FormData();
    if (this.attachments.length != 0) {
      for (const file of this.attachments) {
        formData.append('file', file);
        this.filesSize += file.size;
      }
      console.log(formData);
      console.log(this.filesSize);
      if (this.filesSize > 10485760) {
        valid = false;
        this.filesSize = 0;
      }
    }

    if (valid) {
      if (this.attachments.length != 0) {
        this.thereAttach = "yes";
        //attachment request
        this.serviceObj.sendAttachReq(formData).subscribe((res: any) => {
          console.log(res);
        });
      }
      //all message parts except attachment request
      console.log(this.reciver + " " + this.subject + " " + this.message)
      this.serviceObj.composeMessage(this.reciver + "$" + this.subject + "$" + this.message + "$" + this.thereAttach).subscribe((res: any) => {
        console.log(res);
      });
    }
    else
      console.log("over size")
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
}
