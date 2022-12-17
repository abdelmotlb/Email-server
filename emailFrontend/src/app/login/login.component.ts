import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	constructor(private logInService: LoginService ) { }

	userName: string = "";
	password: string = "";
	userData: string = "";

	setPass(gotPass: string){ this.password = gotPass; }
	setUserName(gotUserName: string){ this.userName = gotUserName; }

	ngOnInit(): void {
	}

	login(){
		this.userData = this.userName + "$" + this.password;
		console.log(this.userData);
		this.logInService.logIn(this.userData).subscribe((loginMessage: any) => {
			if(loginMessage == true){
				console.log('in data base');
			}else{
				console.log('not in data base');
			}
		});
	}
	signUp(){
		this.userData = this.userName + "$" + this.password;
		console.log(this.userData);
		this.logInService.signUp(this.userData).subscribe((loginMessage: any) => {
			if(loginMessage == true){
				console.log('in data base s');
			}else{
				console.log('not in data base s');
			}
		});
	}

}
