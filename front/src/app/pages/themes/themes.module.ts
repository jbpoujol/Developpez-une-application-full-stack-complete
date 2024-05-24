import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemesPageComponent } from './themes-page/themes-page.component';
import { RouterModule, Routes } from '@angular/router';
import { ThemesFeatureModule } from 'src/app/features/themes/themes-feature.module';

const routes: Routes = [
  {
    path: '',
    component: ThemesPageComponent,
  },
];

@NgModule({
  declarations: [ThemesPageComponent],
  imports: [CommonModule, RouterModule.forChild(routes), ThemesFeatureModule],
})
export class ThemesModule {}
