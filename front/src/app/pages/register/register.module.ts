import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterPageComponent } from './register-page/register-page.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: RegisterPageComponent,
  },
];

@NgModule({
  declarations: [RegisterPageComponent],
  imports: [CommonModule, RouterModule.forChild(routes)],
})
export class RegisterModule {}
