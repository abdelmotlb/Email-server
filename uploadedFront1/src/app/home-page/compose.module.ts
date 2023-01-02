import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { ComposeComponent } from './compose/compose.component';
import { ContactsComponent } from './contacts/contacts.component';
import { MessageComponent } from './message/message.component';

const MaterialModules = [
  MatDialogModule,
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatSelectModule,
  MatCheckboxModule,
];

@NgModule({
  declarations: [
    ComposeComponent,
    ContactsComponent,
    MessageComponent
  ],
  imports: [CommonModule, MaterialModules, ReactiveFormsModule],
  exports: [MaterialModules],
})
export class ComposeModule {}
