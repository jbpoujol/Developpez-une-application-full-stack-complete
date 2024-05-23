import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticlesPageComponent } from './articles-page/articles-page.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: ArticlesPageComponent,
  },
];

@NgModule({
  declarations: [ArticlesPageComponent],
  imports: [CommonModule, RouterModule.forChild(routes)],
})
export class ArticlesModule {}
