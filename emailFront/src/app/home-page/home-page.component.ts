import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { ComposeComponent } from './compose/compose.component';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private router: Router, private dialogObj: MatDialog) { }

  ngOnInit(): void {
  }

  appearCompose(){
    const Obj = this.dialogObj.open(ComposeComponent, {
      height: '600px',
      width: '800px',
      data: {
        message: 'Hello World from Edupala',
      },
    });
  }

  gotoInbox(){
    this.router.navigate(['/home/inbox']);
  }

  gotoDraft(){
    this.router.navigate(['/home/draft']);
  }

  gotoTrash(){
    this.router.navigate(['/home/trash']);
  }

  gotoSent(){
    this.router.navigate(['/home/sent']);
  }
}


