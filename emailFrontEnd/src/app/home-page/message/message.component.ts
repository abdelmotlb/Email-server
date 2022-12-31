import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IMessage } from '../home-page.component';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<MessageComponent>,@Inject(MAT_DIALOG_DATA) public data: IMessage) { }

  ngOnInit(): void {
    console.log(this.data);
  }

  

}
