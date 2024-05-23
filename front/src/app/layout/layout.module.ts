import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { MinimalLayoutComponent } from './components/minimal-layout/minimal-layout.component';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [HeaderComponent, MinimalLayoutComponent, MainLayoutComponent],
  imports: [CommonModule, MenubarModule, ButtonModule],
  exports: [MinimalLayoutComponent, MainLayoutComponent],
})
export class LayoutModule {}
