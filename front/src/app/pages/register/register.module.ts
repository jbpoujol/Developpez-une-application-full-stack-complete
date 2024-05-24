import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterPageComponent } from './register-page/register-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthModule } from 'src/app/auth/auth.module';

const routes: Routes = [
  {
    path: '',
    component: RegisterPageComponent,
  },
];

@NgModule({
  declarations: [RegisterPageComponent],
  imports: [CommonModule, RouterModule.forChild(routes), AuthModule],
})
export class RegisterModule {}
