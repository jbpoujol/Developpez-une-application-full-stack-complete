import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Article, Comment } from '../models';
import { EnvironmentService } from '@core';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private articlesPath = '/articles';
  private subscribedArticlesPath = '/articles/subscribed';

  private articlesSubject = new BehaviorSubject<Article[]>([]);
  private subscribedArticlesSubject = new BehaviorSubject<Article[]>([]);
  private articleSubject = new BehaviorSubject<Article | null>(null);

  articles$ = this.articlesSubject.asObservable();
  subscribedArticles$ = this.subscribedArticlesSubject.asObservable();
  article$ = this.articleSubject.asObservable();

  constructor(
    private http: HttpClient,
    private environment: EnvironmentService
  ) {}

  getAllArticles(): void {
    this.http
      .get<Article[]>(`${this.environment.apiUrl}${this.articlesPath}`)
      .pipe(tap((articles) => this.articlesSubject.next(articles)))
      .subscribe();
  }

  getSubscribedArticles(): void {
    this.http
      .get<Article[]>(
        `${this.environment.apiUrl}${this.subscribedArticlesPath}`
      )
      .pipe(tap((articles) => this.subscribedArticlesSubject.next(articles)))
      .subscribe();
  }

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

  getArticle(articleId: number): void {
    this.http
      .get<Article>(
        `${this.environment.apiUrl}${this.articlesPath}/${articleId}`
      )
      .pipe(tap((article) => this.articleSubject.next(article)))
      .subscribe();
  }

  addComment(articleId: number, content: string): Observable<Comment> {
    return this.http
      .post<Comment>(
        `${this.environment.apiUrl}${this.articlesPath}/${articleId}/comments`,
        { content }
      )
      .pipe(tap(() => this.getArticle(articleId)));
  }
}
