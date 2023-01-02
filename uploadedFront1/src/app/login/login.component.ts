import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	login: boolean = true;
	static activeUser: string = "";

	constructor(private logInService: LoginService, private router: Router) { }
	userData: string = "";

	// login input data
	Email: string = "";
	password: string = "";
	
	// sign up input data
	firstName: string = "";
	lastName: string = "";
	genderIndicator: boolean = true;
	gender: string = "Male";


	// setters of got data
	setPass(gotPass: string) { this.password = gotPass; }
	setEmail(gotEmail: string) { this.Email = gotEmail; }
	setFirstName(gotFirstName: string){ this.firstName = gotFirstName; }
	setLastName(gotLastName: string){ this.lastName = gotLastName; }

	// activeUser = this.Email
	ngOnInit(): void {
		LoginComponent.activeUser = "";
	}

	signInFn() {
		this.userData = this.Email + "$" + this.password;
		console.log(this.userData);
		this.logInService.logIn(this.userData).subscribe((loginMessage: any) => {
			if (loginMessage == true) {
				LoginComponent.activeUser = this.Email;
				console.log('correct email and password');
				this.router.navigate(['/home']);
			} else {
				alert('wrong email or password');
			}
		});
	}

	signUpFn() {

		if( this.genderIndicator == false){
			this.gender = "Female";
		}

		this.userData = this.Email + "$" + this.password + "$" + this.firstName + "$" + this.lastName + "$" + this.gender;
		console.log(this.userData);
		this.logInService.signUp(this.userData).subscribe((loginMessage: any) => {
			if (loginMessage == true) {
				LoginComponent.activeUser = this.Email;
				console.log('successful sign up');
				this.router.navigate(['/home']);
			} else {
				alert('can not sign up');
			}
		});
	}

}
