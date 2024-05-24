import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticlesPageComponent } from './articles-page/articles-page.component';
import { RouterModule, Routes } from '@angular/router';
import { ArticleFeatureModule } from 'src/app/features/article/article-feature.module';
import { CreateArticlePageComponent } from './create-article-page/create-article-page.component';

const routes: Routes = [
  {
    path: '',
    component: ArticlesPageComponent,
  },
  {
    path: 'create',
    component: CreateArticlePageComponent,
  },
];

@NgModule({
  declarations: [ArticlesPageComponent, CreateArticlePageComponent],
  imports: [CommonModule, RouterModule.forChild(routes), ArticleFeatureModule],
})
export class ArticlesModule {}
