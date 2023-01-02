import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/login/login.component';
import { HomePageComponent, IMessage } from '../home-page.component';
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
	selectedItems: boolean[] = [];
	userFolders: string[] = [];
	show: IMessage[] = [];
	

	getToShow(){ return HomePageComponent.toShow; }
	constructor(private home: HomeService, private dialogObj: MatDialog) { }
	ngOnInit(): void {

		this.selectedItems = new Array(9).fill(false);
		console.log('in inbox');
		console.log(HomePageComponent.toShow);
		console.log('in reset' + this.page + " " + HomePageComponent.toShow.length);
		for (let index = 0; (this.page-1) * this.messagesPerPage + index < HomePageComponent.toShow.length ; index++) {
			this.selectedItems[index] = false;
			console.log("in reset checked" + index);
		}

		this.show = HomePageComponent.toShow;

	}

	// selection functions
	resetChecked(){
		for (let index = 0; (this.page-1) * this.messagesPerPage + index < HomePageComponent.toShow.length ; index++) {
			this.selectedItems[index] = false;
			console.log("in reset checked" + index);
		}
	}
	isChecked(evt: any, index: any){
		this.selectedItems[index] = evt.target.checked;
		console.log(index + " " + this.selectedItems);
	}

	// delete functions
	collectDeleted_request(){

		let overallDeleted = LoginComponent.activeUser + "$" + HomePageComponent.windowUsed;
		let selectedItemsNum = 0;

		// generate the string >>
		for (let index = 0; (this.page-1) * this.messagesPerPage + index < HomePageComponent.toShow.length ; index++) {
			if( this.selectedItems[index] ){
				console.log('in create overall deleting ' + index);
				console.log('date ' + HomePageComponent.toShow[(this.page-1)*this.messagesPerPage+index].date);
				overallDeleted = overallDeleted + "$" + HomePageComponent.toShow[(this.page-1)*this.messagesPerPage+index].id;
				selectedItemsNum++;
			}
		}
		console.log(this.selectedItems);
		console.log('active user: ' +  LoginComponent.activeUser);

		// request if the selected items is not 0
		if( selectedItemsNum != 0 ){
			console.log("before request" + overallDeleted);
			// request to get the array after deleting.
			this.home.deleteSelected(overallDeleted).subscribe((res: IMessage[]) => {
				HomePageComponent.toShow = res;
				console.log(HomePageComponent.toShow);
			});	
		}

	}

	// move functions
	getUserFolder(){
		this.home.getFolders(LoginComponent.activeUser).subscribe((res: string[]) => {
			this.userFolders = res;
		});
	}

	collectMoved_request(index: any){
		let overallMoved =  LoginComponent.activeUser + "$" + HomePageComponent.windowUsed + "$" +this.userFolders[index];
		let selectedItemsNum = 0;

		for (let i = 0; (this.page-1) * this.messagesPerPage + i < HomePageComponent.toShow.length ; i++) {
			if( this.selectedItems[i] ){
				overallMoved = overallMoved + "$" + HomePageComponent.toShow[(this.page-1)*this.messagesPerPage+i].id;
				selectedItemsNum++;
			}
		}
		

		// request to get the array after deleting.
		if( selectedItemsNum != 0 ){
			console.log("before request" + overallMoved);
			this.home.moveSelected(overallMoved).subscribe((res: IMessage[]) => {
				HomePageComponent.toShow = res;
			});
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
		console.log(HomePageComponent.toShow[(this.page-1) * this.messagesPerPage + index]);
		console.log('i = ' + index);
			

		const Obj = this.dialogObj.open(MessageComponent, {
			height: '700px',
			width: '800px',
			data: HomePageComponent.toShow[(this.page-1) * this.messagesPerPage + index] as IMessage
		});
	}

	search_request(){
		let reqData = LoginComponent.activeUser + "$" + HomePageComponent.windowUsed + "$" + "math";
		this.home.searchRequest(reqData).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
		});
	}

	sort_request(){
		// zero indicate descending.
		let reqData = LoginComponent.activeUser + "$" + HomePageComponent.windowUsed + "$" + "Date" + "$" + "0";
		this.home.sortRequest(reqData).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
		});
	}
	
	filter_request(){
		let reqData = LoginComponent.activeUser + "$" + HomePageComponent.windowUsed + "$" + "both" + "$" + "message1" + "$" + "xyz";
		this.home.filterRequest(reqData).subscribe((res: IMessage[]) => {
			HomePageComponent.toShow = res;
		});
	}
}
