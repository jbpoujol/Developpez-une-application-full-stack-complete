import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Article, Comment } from '../models';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private articlesUrl = 'http://localhost:8080/api/articles';
  private subscribedArticlesUrl =
    'http://localhost:8080/api/articles/subscribed';

  private articlesSubject = new BehaviorSubject<Article[]>([]);
  private subscribedArticlesSubject = new BehaviorSubject<Article[]>([]);
  private articleSubject = new BehaviorSubject<Article | null>(null);

  articles$ = this.articlesSubject.asObservable();
  subscribedArticles$ = this.subscribedArticlesSubject.asObservable();
  article$ = this.articleSubject.asObservable();

  constructor(private http: HttpClient) {}

  getAllArticles(): void {
    this.http
      .get<Article[]>(this.articlesUrl)
      .pipe(tap((articles) => this.articlesSubject.next(articles)))
      .subscribe();
  }

  getSubscribedArticles(): void {
    this.http
      .get<Article[]>(this.subscribedArticlesUrl)
      .pipe(tap((articles) => this.subscribedArticlesSubject.next(articles)))
      .subscribe();
  }

  addArticle(
    title: string,
    content: string,
    themeId: number
  ): Observable<Article> {
    return this.http
      .post<Article>(this.articlesUrl, { title, content, themeId })
      .pipe(tap(() => this.getSubscribedArticles()));
  }

  getArticle(articleId: number): void {
    this.http
      .get<Article>(`${this.articlesUrl}/${articleId}`)
      .pipe(tap((article) => this.articleSubject.next(article)))
      .subscribe();
  }

  addComment(articleId: number, content: string): Observable<Comment> {
    return this.http
      .post<Comment>(`${this.articlesUrl}/${articleId}/comments`, { content })
      .pipe(tap(() => this.getArticle(articleId)));
  }
}
