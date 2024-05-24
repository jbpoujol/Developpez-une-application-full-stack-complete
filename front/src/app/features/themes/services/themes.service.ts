import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Theme } from '@shared';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private themesUrl = 'http://localhost:8080/api/themes';
  private userThemesUrl = 'http://localhost:8080/api/auth/profile/themes';

  private themesSubject = new BehaviorSubject<Theme[]>([]);
  private userThemesSubject = new BehaviorSubject<Theme[]>([]);

  themes$ = this.themesSubject.asObservable();
  userThemes$ = this.userThemesSubject.asObservable();

  constructor(private http: HttpClient) {}

  getThemes(): void {
    this.http
      .get<Theme[]>(this.themesUrl)
      .pipe(tap((themes) => this.themesSubject.next(themes)))
      .subscribe();
  }

  getUserThemes(): void {
    this.http
      .get<Theme[]>(this.userThemesUrl)
      .pipe(tap((themes) => this.userThemesSubject.next(themes)))
      .subscribe();
  }

  subscribeToTheme(themeId: number): Observable<any> {
    return this.http
      .post(`${this.themesUrl}/${themeId}/subscribe`, {})
      .pipe(tap(() => this.getUserThemes()));
  }

  unsubscribeFromTheme(themeId: number): Observable<any> {
    return this.http
      .post(`${this.themesUrl}/${themeId}/unsubscribe`, {})
      .pipe(tap(() => this.getUserThemes()));
  }
}
