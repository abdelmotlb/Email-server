import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/login/login.component';
import { IMessage } from '../home-page.component';
import { HomeService } from '../home.service';
import { MessageComponent } from '../message/message.component';



@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

	messagesPerPage: number = 9;
	page: number = 1;
	toShow: IMessage[] = [];
	selectedItems: boolean[] = [];
	windowUsed: string = "inbox";

	constructor(private home: HomeService, private dialogObj: MatDialog) { }
	ngOnInit(): void {

		console.log('in inbox');
		this.resetChecked();
		this.home.getMessages("inbox").subscribe((res: IMessage[]) => { 
			this.toShow = res;
			console.log(this.toShow);
		});

	}

	// selection functions
	resetChecked(){
		for (let index = 0; this.page * this.messagesPerPage + index < this.toShow.length ; index++) {
			this.selectedItems[index] = false;
		}
	}
	isChecked(evt: any, index: any){
		this.selectedItems[index] = evt.target.checked;
	}

	// delete functions
	collectDeleted_request(){
		let overallDeleted = LoginComponent.activeUser + "$" + this.windowUsed;
		let selectedItemsNum = 0;

		// generate the string >>
		for (let index = 0; this.page * this.messagesPerPage + index < this.toShow.length ; index++) {
			if( this.selectedItems[index] ){
				// overallDeleted = overallDeleted + "$" + this.toShow[this.page*this.messagesPerPage+index].date;
				selectedItemsNum++;
			}
		}

		// request if the selected items is not 0
		if( selectedItemsNum != 0 ){
			// request to get the array after deleting.
			this.home.deleteSelected(overallDeleted).subscribe((res: IMessage[]) => {
				this.toShow = res;
			});	
		}

		
	}

	// move functions
	collectMoved_request(){

		// call function printcounter to get selected folder.

		// let overallMoved =  LoginComponent.activeUser + "$" + this.windowUsed + "$" + this.folderName;
		let selectedItemsNum = 0;

		for (let index = 0; this.page * this.messagesPerPage + index < this.toShow.length ; index++) {
			if( this.selectedItems[index] ){
				// overallMoved = overallMoved + "$" + this.toShow[this.page*this.messagesPerPage+index].date;
				selectedItemsNum++;
			}
		}
		

		// request to get the array after deleting.
		if( selectedItemsNum != 0 ){
			// this.home.moveSelected(overallMoved).subscribe((res: IMessage[]) => {
			// 	this.toShow = res;
			// });
		}
		
	}

	// elementNum = 0;
	// pagesCheckbox: number[][] = [][10];
	// list = [
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": []},
	// 	{"id": 1, "subject": "data processingb", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processingc", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processingd", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinge", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processingg", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": []},
	// 	{"id": 1, "subject": "data processingh", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},
	// 	{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022", "att": ["1.pdf", "2.pdf", "3.pdf"]},



	// ];
	// incrementNum(){ this.elementNum++; }


	showMessage(index: any){

		console.log('in show Message');
		console.log(this.page);
		console.log(this.toShow[(this.page-1) * this.messagesPerPage + index]);
		console.log('i = ' + index);
			

		const Obj = this.dialogObj.open(MessageComponent, {
			height: '700px',
			width: '800px',
			data: this.toShow[(this.page-1) * this.messagesPerPage + index] as IMessage
		});
	}

	printcounter(index: any){
		console.log(index);
	}
}
