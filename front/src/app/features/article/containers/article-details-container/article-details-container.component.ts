import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { Article, Comment } from '../../models';
import { Subject, takeUntil } from 'rxjs';
import { MessageService } from 'primeng/api';

/**
 * ArticleDetailsContainerComponent is responsible for displaying the details of an article,
 * including its comments. It handles the addition of new comments.
 */
@Component({
  selector: 'app-article-details-container',
  templateUrl: './article-details-container.component.html',
  styleUrls: ['./article-details-container.component.scss'],
})
export class ArticleDetailsContainerComponent implements OnInit, OnDestroy {
  /** The article to be displayed */
  article?: Article;

  /** Subject used to unsubscribe from observables when the component is destroyed */
  private destroy$ = new Subject<boolean>();

  /**
   * Creates an instance of ArticleDetailsContainerComponent.
   * @param route - The activated route to get the article ID from the URL
   * @param articleService - The service used to fetch article details and add comments
   * @param messageService - The message service used to display toast notifications
   */
  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private messageService: MessageService
  ) {}

  /**
   * Initializes the component by fetching the article details based on the article ID from the URL.
   */
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

  /**
   * Handles the submission of a new comment.
   * Adds the comment to the article's comments if the article exists.
   * Displays a success toast notification on success and an error toast notification on failure.
   * @param content - The content of the comment
   */
  handleCommentSubmit(content: string): void {
    if (this.article) {
      this.articleService
        .addComment(this.article.id, content)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (comment: Comment) => {
            this.article?.comments.push(comment);
            this.messageService.add({
              severity: 'success',
              summary: 'Comment Added',
              detail: 'Your comment has been added successfully',
            });
          },
          error: () => {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Failed to add comment',
            });
          },
        });
    }
  }

  /**
   * Cleans up the component by unsubscribing from observables.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
