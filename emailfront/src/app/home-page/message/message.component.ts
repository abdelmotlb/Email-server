import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IMessage } from '../home-page.component';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  // variables
  downloadIndicator: boolean = false;
  showedAttachments: string[] = [];
  gotPath: string =  "";
  endMessage: boolean = false;

  // default functions
  setPath(path: string){ this.gotPath = this.gotPath + path; }
  constructor(private homeSerObj: HomeService, private dialogRef: MatDialogRef<MessageComponent>,@Inject(MAT_DIALOG_DATA) public data: IMessage) { }
  ngOnInit(): void {
    console.log(this.data);
    for (let index = 0; index < this.data.attachment.length; index++) {
      let gotString = this.data.attachment[index].split("$");
      this.showedAttachments[index] = gotString[1];
    }
  }

  enableIndicator(){
    this.downloadIndicator = true;
  }
  

  // used functions
  makeFileNum(index: number){
    this.gotPath = "";
    if( index == -1 ){
      for (let i = 0; i < this.data.attachment.length; i++) {
        this.gotPath = this.gotPath + this.data.attachment[i] + "#";
      }
    } else {
      this.gotPath = this.gotPath + this.data.attachment[index] + "#";
    }
  }

  downloadedFile(){
    this.homeSerObj.download(this.gotPath).subscribe((checkSuccess: any) => {
      console.log(checkSuccess);
    })
  }

}
