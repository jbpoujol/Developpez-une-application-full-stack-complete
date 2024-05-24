import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { ReactiveFormsModule } from '@angular/forms';

// PrimeNG Modules
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { RouterModule } from '@angular/router';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { DividerModule } from 'primeng/divider';
import { CardModule } from 'primeng/card';

@NgModule({
  declarations: [LoginFormComponent, ProfileFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    MessagesModule,
    MessageModule,
    RouterModule,
    DividerModule,
    CardModule,
  ],
  exports: [LoginFormComponent, ProfileFormComponent],
})
export class AuthModule {}
