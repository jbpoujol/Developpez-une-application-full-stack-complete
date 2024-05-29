import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Article, Comment } from '../models';
import { EnvironmentService } from '@core';

/**
 * ArticleService is responsible for managing articles and comments.
 * It provides methods to fetch, add, and update articles and comments.
 */
@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  /** Path to the articles API endpoint */
  private articlesPath = '/articles';
  /** Path to the subscribed articles API endpoint */
  private subscribedArticlesPath = '/articles/subscribed';

  /** BehaviorSubject to hold the list of all articles */
  private articlesSubject = new BehaviorSubject<Article[]>([]);
  /** BehaviorSubject to hold the list of subscribed articles */
  private subscribedArticlesSubject = new BehaviorSubject<Article[]>([]);
  /** BehaviorSubject to hold the details of a single article */
  private articleSubject = new BehaviorSubject<Article | null>(null);

  /** Observable of the list of all articles */
  articles$ = this.articlesSubject.asObservable();
  /** Observable of the list of subscribed articles */
  subscribedArticles$ = this.subscribedArticlesSubject.asObservable();
  /** Observable of the details of a single article */
  article$ = this.articleSubject.asObservable();

  /**
   * Creates an instance of ArticleService.
   * @param http - The HTTP client for making requests
   * @param environment - The environment service for accessing environment variables
   */
  constructor(
    private http: HttpClient,
    private environment: EnvironmentService
  ) {}

  /**
   * Fetches all articles and updates the articlesSubject.
   */
  getAllArticles(): void {
    this.http
      .get<Article[]>(`${this.environment.apiUrl}${this.articlesPath}`)
      .pipe(tap((articles) => this.articlesSubject.next(articles)))
      .subscribe();
  }

  /**
   * Fetches subscribed articles and updates the subscribedArticlesSubject.
   */
  getSubscribedArticles(): void {
    this.http
      .get<Article[]>(
        `${this.environment.apiUrl}${this.subscribedArticlesPath}`
      )
      .pipe(tap((articles) => this.subscribedArticlesSubject.next(articles)))
      .subscribe();
  }

  /**
   * Adds a new article and updates the subscribed articles list.
   * @param title - The title of the article
   * @param content - The content of the article
   * @param themeId - The ID of the theme associated with the article
   * @returns An Observable of the newly added article
   */
  addArticle(
    title: string,
    content: string,
    themeId: number
  ): Observable<Article> {
    return this.http
      .post<Article>(`${this.environment.apiUrl}${this.articlesPath}`, {
        title,
        content,
        themeId,
      })
      .pipe(tap(() => this.getSubscribedArticles()));
  }

  /**
   * Fetches the details of a specific article and updates the articleSubject.
   * @param articleId - The ID of the article to fetch
   */
  getArticle(articleId: number): void {
    this.http
      .get<Article>(
        `${this.environment.apiUrl}${this.articlesPath}/${articleId}`
      )
      .pipe(tap((article) => this.articleSubject.next(article)))
      .subscribe();
  }

  /**
   * Adds a comment to a specific article and updates the article details.
   * @param articleId - The ID of the article to add the comment to
   * @param content - The content of the comment
   * @returns An Observable of the newly added comment
   */
  addComment(articleId: number, content: string): Observable<Comment> {
    return this.http
      .post<Comment>(
        `${this.environment.apiUrl}${this.articlesPath}/${articleId}/comments`,
        { content }
      )
      .pipe(tap(() => this.getArticle(articleId)));
  }
}
