import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { ComposeComponent } from './compose/compose.component';
import { HomeService } from './home.service';
import { InboxComponent } from './inbox/inbox.component';

export interface IMessage {
	from: string
	to: string
	subject: string
	message: string
	priorty: string
	attachment: string[]
	date: string
	id: string
}

@Component({
	selector: 'app-home-page',
	templateUrl: './home-page.component.html',
	styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

	// variables
	toShow: string[] = [
		"abdo", "hesham", "mohamed", "eslam"
	];
	userFolders: string[] = [];
	static windowUsed: string = "";
	renameIndicators: boolean[] = [];
	newName: string = "";
	addFolderIndicator: boolean = false;
	static toShow: IMessage[] = [];

	setNewName(got: string) { this.newName = got; }
	constructor(private router: Router, private dialogObj: MatDialog, private home: HomeService) { }

	ngOnInit(): void {
		console.log('in ng on it for home');
		if (LoginComponent.activeUser == "") {
			this.router.navigate(['']);
		}
	}

	getActiveuser() { return LoginComponent.activeUser; }

	// nagivation functions
	appearCompose() {
		const Obj = this.dialogObj.open(ComposeComponent, {
			height: '700px',
			width: '800px'
		});
	}

	getwindowUsed() {
		return HomePageComponent.windowUsed
	}



	gotoInbox() {
		HomePageComponent.windowUsed = "inbox";
		this.home.getMessages(HomePageComponent.windowUsed).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
			this.router.navigate(['/home/inbox']);
		});

	}

	gotoDraft() {
		HomePageComponent.windowUsed = "draft";
		this.home.getMessages(HomePageComponent.windowUsed).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
			this.router.navigate(['/home/inbox']);
		});
	}

	gotoTrash() {
		HomePageComponent.windowUsed = "trash";
		this.home.getMessages(HomePageComponent.windowUsed).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
			this.router.navigate(['/home/inbox']);
		});
	}

	gotoSent() {
		HomePageComponent.windowUsed = "sent";
		this.home.getMessages(HomePageComponent.windowUsed).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
			this.router.navigate(['/home/inbox']);
		});

	}

	gotoContacts() {
		this.router.navigate(['/home/contacts']);
	}

	// folder operations
	printcounter(index: any) {
		console.log(index);
	}

	getFolders() {
		this.home.getFolders(LoginComponent.activeUser).subscribe((res: string[]) => {
			this.userFolders = res;
			this.renameIndicators = new Array(res.length).fill(false);
		});
	}

	// open
	openSelectedFolder(index: any) {
		HomePageComponent.windowUsed = this.userFolders[index];
		this.home.getMessages(HomePageComponent.windowUsed).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
			this.router.navigate(['/home/inbox']);
		});
	}

	resetChecked() {
		for (let index = 0; index < this.renameIndicators.length; index++) {
			this.renameIndicators[index] = false;
			console.log("in reset checked" + index);
		}
	}

	// deleted
	deletedSelectedFolder(index: any) {
		this.resetChecked();
		let selectedFolderInfo = LoginComponent.activeUser + "$" + this.userFolders[index];
		this.home.deleteFolder(selectedFolderInfo).subscribe((res: string[]) => {
			this.userFolders = res;
			this.renameIndicators = new Array(res.length).fill(false);
		});
	}

	openRenameAreaCheck(index: any) {
		let oneFolderRenaming = 0;
		for (let i = 0; i < this.renameIndicators.length; i++) {
			if (this.renameIndicators[i]) {
				oneFolderRenaming++;
				break;
			}
		}
		if (oneFolderRenaming == 0) {
			this.renameIndicators[index] = true;
		}
	}

	handlecreateFolderOp() {
		let oneFolderRenaming = 0;
		for (let i = 0; i < this.renameIndicators.length; i++) {
			if (this.renameIndicators[i]) {
				oneFolderRenaming++;
				break;
			}
		}
		if (oneFolderRenaming == 0) {
			this.addFolderIndicator = true;
		}

	}

	renameConfirm(index: any) {
		let selectedFolderInfo = LoginComponent.activeUser + "$" + this.userFolders[index] + "$" + this.newName;
		this.home.renameFolder(selectedFolderInfo).subscribe((res: string[]) => {
			this.userFolders = res;
			this.renameIndicators = new Array(res.length).fill(false);
		});
	}

	createFolder() {
		let selectedFolderInfo = LoginComponent.activeUser + "$" + this.newName;
		console.log(" selected in create folder " + selectedFolderInfo);
		this.home.createFolder(selectedFolderInfo).subscribe((res: string[]) => {
			this.userFolders = res;
			this.renameIndicators = new Array(res.length).fill(false);
		});
	}

	// sign out
	signOut() {
		LoginComponent.activeUser = "";
		this.router.navigate(['']);
	}


}


