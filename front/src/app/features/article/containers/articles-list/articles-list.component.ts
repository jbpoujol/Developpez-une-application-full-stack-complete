import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { ActivatedRoute, Router } from '@angular/router';

/**
 * ArticlesListComponent is responsible for displaying a list of subscribed articles.
 * It handles navigation to the article details when an article is clicked.
 */
@Component({
  selector: 'app-articles-list',
  templateUrl: './articles-list.component.html',
  styleUrls: ['./articles-list.component.scss'],
})
export class ArticlesListComponent implements OnInit {
  /** Observable of the list of subscribed articles */
  subscribedArticles$ = this.articleService.subscribedArticles$;

  /**
   * Creates an instance of ArticlesListComponent.
   * @param articleService - The service used to fetch subscribed articles
   * @param router - The router for navigation
   * @param route - The activated route
   */
  constructor(
    private articleService: ArticleService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  /**
   * Initializes the component by fetching the subscribed articles.
   */
  ngOnInit(): void {
    this.articleService.getSubscribedArticles();
  }

  /**
   * Handles the click event on an article.
   * Navigates to the article details page.
   * @param articleId - The ID of the clicked article
   */
  onArticleClick(articleId: number): void {
    this.router.navigateByUrl(`/articles/details/${articleId}`);
  }
}
