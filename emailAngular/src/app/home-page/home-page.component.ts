import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  gotoInbox(){
    this.router.navigate(['/home/inbox']);
  }

  gotoDraft(){
    this.router.navigate(['/home/draft']);
  }
}
