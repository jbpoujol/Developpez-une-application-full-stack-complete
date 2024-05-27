import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthModule } from 'src/app/auth/auth.module';
import { ThemesFeatureModule } from 'src/app/features/themes/themes-feature.module';
import { DividerModule } from 'primeng/divider';
import { ProfileFeatureModule } from '@profile';

const routes: Routes = [
  {
    path: '',
    component: ProfilePageComponent,
  },
];

@NgModule({
  declarations: [ProfilePageComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    AuthModule,
    ThemesFeatureModule,
    DividerModule,
    ProfileFeatureModule,
  ],
})
export class ProfileModule {}
