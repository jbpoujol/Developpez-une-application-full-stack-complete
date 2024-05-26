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
import { ArticleDetailsContainerComponent } from './containers/article-details-container/article-details-container.component';
import { ArticleDetailComponent } from './components/article-detail/article-detail.component';
import { ArticleCommentComponent } from './components/article-comment/article-comment.component';
import { CommentFormComponent } from './components/comment-form/comment-form.component';
import { DividerModule } from 'primeng/divider';

@NgModule({
  declarations: [
    ArticlesListComponent,
    AddArticleComponent,
    ArticleCardComponent,
    ArticleDetailsContainerComponent,
    ArticleDetailComponent,
    ArticleCommentComponent,
    CommentFormComponent,
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
    DividerModule,
  ],
  exports: [
    ArticlesListComponent,
    AddArticleComponent,
    ArticleDetailsContainerComponent,
  ],
})
export class ArticleFeatureModule {}
