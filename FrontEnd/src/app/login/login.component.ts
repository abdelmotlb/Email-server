import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  static USERNAME: any;

	constructor(private logInService: LoginService, private router: Router ) { }

	userName: string = "";
	password: string = "";
	userData: string = "";
	

	setPass(gotPass: string){ this.password = gotPass; }
	setUserName(gotUserName: string){ this.userName = gotUserName; }
    USERNAME = this.userName
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

			this.router.navigate(['/home']);

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

			this.router.navigate(['/home']);

		});
	}

}
