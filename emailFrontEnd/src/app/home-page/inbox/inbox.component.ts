import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { IMessage } from '../home-page.component';
import { HomeService } from '../home.service';
import { MessageComponent } from '../message/message.component';



@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

	page: number = 1;
	toShow: any;
	selectedItems: boolean[] = [];
	checkShowWindow: boolean = false;

	constructor(private home: HomeService, private dialogObj: MatDialog) { }
	ngOnInit(): void {

		this.checkShowWindow = false;
		console.log('in inbox');
		this.resetChecked();
		this.home.getMessages("inbox").subscribe((res: IMessage[]) => { 
			this.toShow = res;
			console.log(this.toShow);
		});

	}

	resetChecked(){
		for (let index = 0; index < 10; index++) {
			this.selectedItems[index] = false;
		}
	}

	elementNum = 0;
	pagesCheckbox: number[][] = [][10];
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
	incrementNum(){ this.elementNum++; }
	isChecked(evt: any, index: any){
		this.selectedItems[index] = evt.target.checked;
		this.checkShowWindow = true;
		console.log(this.checkShowWindow);
	}

	showMessage(index: any){

		console.log('in show Message');
		console.log(this.page);
		console.log(this.toShow[(this.page-1) * 10 + index]);
		console.log('i = ' + index);

		if(this.checkShowWindow){
			this.checkShowWindow = false;
			console.log('show');
			return;
		}
			

		const Obj = this.dialogObj.open(MessageComponent, {
			height: '700px',
			width: '800px',
			data: this.toShow[(this.page-1) * 10 + index] as IMessage
		});
	}

}
