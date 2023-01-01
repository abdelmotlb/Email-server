import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { ComposeComponent, IPerson } from './compose/compose.component';
import { HomeService } from './home.service';

export interface IMessage {
	from: string
	to: string
	subject: string
	message: string
	attachment: string[]
}

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  	constructor(private router: Router, private dialogObj: MatDialog, private home: HomeService) { }

	ngOnInit(): void {
		console.log('in ng on it for home');
		if( LoginComponent.activeUser == "" ){
			this.router.navigate(['']);
		}
	}

	getActiveuser(){ return LoginComponent.activeUser; }

	// nagivation functions
	appearCompose() {
		const Obj = this.dialogObj.open(ComposeComponent, {
		height: '600px',
		width: '800px',
		data: [
			{ name: 'simon', age: 32},
			{ name: 'simon', age: 32}
		] as IPerson[]
		});
	}

	gotoInbox() {
		this.router.navigate(['/home/inbox']);
	}

	gotoDraft() {
		this.router.navigate(['/home/draft']);
	}

	gotoTrash() {
		this.router.navigate(['/home/trash']);
	}

	gotoSent() {
		this.router.navigate(['/home/sent']);
	}

	gotoContacts() {
		this.router.navigate(['/home/contacts']);
	}

	signOut(){
		LoginComponent.activeUser = "";
		this.router.navigate(['']);
	}
}


