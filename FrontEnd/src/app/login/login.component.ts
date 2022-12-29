import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	static activeUser: any;

	constructor(private logInService: LoginService, private router: Router) { }

	userName: string = "";
	password: string = "";
	userData: string = "";

	setPass(gotPass: string) { this.password = gotPass; }
	setUserName(gotUserName: string) { this.userName = gotUserName; }
	// activeUser = this.userName
	ngOnInit(): void {
	}

	login() {
		this.userData = this.userName + "$" + this.password;
		console.log(this.userData);
		this.logInService.logIn(this.userData).subscribe((loginMessage: any) => {
			if (loginMessage == true) {
				LoginComponent.activeUser = this.userName;
				console.log('correct email and password');
				this.router.navigate(['/home']);
			} else {
				console.log('wrong email and password');
			}
		});
	}

	signUp() {
		this.userData = this.userName + "$" + this.password;
		console.log(this.userData);
		this.logInService.signUp(this.userData).subscribe((loginMessage: any) => {
			if (loginMessage == true) {
				LoginComponent.activeUser = this.userName;
				console.log('successful sign up');
				this.router.navigate(['/home']);
			} else {
				console.log('can not sign up');
			}
		});
	}

}
