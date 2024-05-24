import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticlesListComponent } from './containers/articles-list/articles-list.component';
import { AddArticleComponent } from './containers/add-article/add-article.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';
import { ArticleCardComponent } from './components/article-card/article-card.component';
import { CardModule } from 'primeng/card';

@NgModule({
  declarations: [
    ArticlesListComponent,
    AddArticleComponent,
    ArticleCardComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    DropdownModule,
    InputTextModule,
    InputTextareaModule,
    ButtonModule,
    CardModule,
  ],
  exports: [ArticlesListComponent, AddArticleComponent],
})
export class ArticleFeatureModule {}
