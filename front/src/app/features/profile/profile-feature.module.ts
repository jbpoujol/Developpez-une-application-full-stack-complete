import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileFormComponent } from './containers/profile-form/profile-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

@NgModule({
  declarations: [ProfileFormComponent],
  imports: [CommonModule, ReactiveFormsModule, ButtonModule, InputTextModule],
  exports: [ProfileFormComponent],
})
export class ProfileFeatureModule {}
