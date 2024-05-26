import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { Article, Comment } from '../../models';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-article-details-container',
  templateUrl: './article-details-container.component.html',
  styleUrls: ['./article-details-container.component.scss'],
})
export class ArticleDetailsContainerComponent implements OnInit, OnDestroy {
  article?: Article;

  destroy$ = new Subject<boolean>();

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) {}

  ngOnInit(): void {
    const articleId = this.route.snapshot.paramMap.get('id');
    if (articleId) {
      this.articleService.getArticle(+articleId);
      this.articleService.article$
        .pipe(takeUntil(this.destroy$))
        .subscribe((article) => {
          if (article) {
            this.article = article;
          }
        });
    }
  }

  handleCommentSubmit(content: string): void {
    if (this.article) {
      this.articleService
        .addComment(this.article.id, content)
        .pipe(takeUntil(this.destroy$))
        .subscribe((comment: Comment) => {
          this.article?.comments.push(comment);
        });
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
