import { Component, OnInit } from '@angular/core';
import { IMessage } from '../home-page.component';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-draft',
  templateUrl: './draft.component.html',
  styleUrls: ['./draft.component.css']
})
export class DraftComponent implements OnInit {

  constructor(private home: HomeService) { }
  toShow: any;
  ngOnInit(): void {
    this.home.getMessages("draft").subscribe((res: IMessage[]) => { this.toShow = res });
  }

  p: number = 1;
	elementNum = 0;
	pagesCheckbox: number[][] = [][10];

	list = [
		{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingb", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingc", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingd", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processinge", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingg", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingh", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processinga", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingb", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingc", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingd", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processinge", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingg", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingh", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processing", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processing", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processing", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processing", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},
		{"id": 1, "subject": "data processingf", "discription": "we have alot of data processed", "date": "25 May 2022"},


	];

	incrementNum(){ this.elementNum++; }
}
