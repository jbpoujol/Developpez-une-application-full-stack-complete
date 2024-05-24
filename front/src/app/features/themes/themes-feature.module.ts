import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemesListComponent } from './containers/themes-list/themes-list.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';

@NgModule({
  declarations: [ThemesListComponent, ThemeCardComponent],
  imports: [CommonModule, ButtonModule, CardModule],
  exports: [ThemesListComponent],
})
export class ThemesFeatureModule {}
