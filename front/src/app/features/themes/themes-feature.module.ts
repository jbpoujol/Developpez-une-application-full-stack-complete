import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemesListComponent, UserThemesComponent } from './containers';
import { ThemeCardModule } from '@shared';

@NgModule({
  declarations: [ThemesListComponent, UserThemesComponent],
  imports: [CommonModule, ThemeCardModule],
  exports: [ThemesListComponent, UserThemesComponent],
})
export class ThemesFeatureModule {}
