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

  articles$ = this.articlesSubject.asObservable();
  subscribedArticles$ = this.subscribedArticlesSubject.asObservable();

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

  getArticle(articleId: number): Observable<Article> {
    return this.http.get<Article>(`${this.articlesUrl}/${articleId}`);
  }

  addComment(articleId: number, content: string): Observable<Comment> {
    return this.http
      .post<Comment>(`${this.articlesUrl}/${articleId}/comments`, { content })
      .pipe(tap(() => this.getSubscribedArticles()));
  }
}
