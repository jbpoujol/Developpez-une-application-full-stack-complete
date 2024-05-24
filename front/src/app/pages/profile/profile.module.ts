import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthModule } from 'src/app/auth/auth.module';

const routes: Routes = [
  {
    path: '',
    component: ProfilePageComponent,
  },
];

@NgModule({
  declarations: [ProfilePageComponent],
  imports: [CommonModule, RouterModule.forChild(routes), AuthModule],
})
export class ProfileModule {}
