import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Route, RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomePageComponent } from './home-page/home-page.component';

const routes : Routes = [
	{path: '', component: LoginComponent},
  {path: 'home', component: HomePageComponent, children: [
	
  ]}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports:  [RouterModule],
})
export class AppRoutingModule { }
