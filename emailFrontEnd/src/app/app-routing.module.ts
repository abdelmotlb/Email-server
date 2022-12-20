import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Route, RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomePageComponent } from './home-page/home-page.component';
import { InboxComponent } from './home-page/inbox/inbox.component';
import { DraftComponent } from './home-page/draft/draft.component';

const routes : Routes = [
	{path: '', component: LoginComponent},
  	{path: 'home', component: HomePageComponent, children: [
		{path: 'inbox', component: InboxComponent},
		{path: 'draft', component: DraftComponent}
  	]}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports:  [RouterModule],
})
export class AppRoutingModule { }
