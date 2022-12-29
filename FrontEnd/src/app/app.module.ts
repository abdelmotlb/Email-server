import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { HomePageComponent } from './home-page/home-page.component';
import { HttpClientModule } from '@angular/common/http';
import { InboxComponent } from './home-page/inbox/inbox.component';
import { DraftComponent } from './home-page/draft/draft.component';
import { TrashComponent } from './home-page/trash/trash.component';
import { SentComponent } from './home-page/sent/sent.component';
import { ComposeComponent } from './home-page/compose/compose.component';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ComposeModule } from './home-page/compose.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    InboxComponent,
    DraftComponent,
    TrashComponent,
    SentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NgbModule,
    FormsModule,
    ComposeModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
