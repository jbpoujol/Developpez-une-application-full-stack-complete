import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemesPageComponent } from './themes-page/themes-page.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: ThemesPageComponent,
  },
];

@NgModule({
  declarations: [ThemesPageComponent],
  imports: [CommonModule, RouterModule.forChild(routes)],
})
export class ThemesModule {}
