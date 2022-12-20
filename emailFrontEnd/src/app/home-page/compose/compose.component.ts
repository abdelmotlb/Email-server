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
  attachment: any;

  setReciver(to: string){
    this.reciver = to;
  }
  setSubject(sub: string){
    this.subject = sub;
  }
  setMessage(mes: string){
    this.message = mes;
  }
  constructor(private dialogRef: MatDialogRef<ComposeComponent> , private serviceObj :HomeService) { }

  ngOnInit(): void {
  }

  sendMessage(){
    this.serviceObj.composeMessage(this.reciver + "$" + this.subject + "$" + this.message).subscribe((res: any) =>{
      console.log(res);
    });
  }

  sendAttach(files: any){
    this.attachment = files;
    console.log(this.attachment);
  }
}
