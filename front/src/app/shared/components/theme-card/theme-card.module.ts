import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ThemeCardComponent } from './theme-card/theme-card.component';

@NgModule({
  declarations: [ThemeCardComponent],
  imports: [CommonModule, ButtonModule, CardModule],
  exports: [ThemeCardComponent],
})
export class ThemeCardModule {}
