import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Theme } from '@shared';
import { EnvironmentService } from '@core';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private themesPath = '/themes';
  private userThemesPath = '/profile/themes';

  private themesSubject = new BehaviorSubject<Theme[]>([]);
  private userThemesSubject = new BehaviorSubject<Theme[]>([]);

  themes$ = this.themesSubject.asObservable();
  userThemes$ = this.userThemesSubject.asObservable();

  constructor(
    private http: HttpClient,
    private environment: EnvironmentService
  ) {}

  getThemes(): void {
    this.http
      .get<Theme[]>(`${this.environment.apiUrl}${this.themesPath}`)
      .pipe(tap((themes) => this.themesSubject.next(themes)))
      .subscribe();
  }

  getUserThemes(): void {
    this.http
      .get<Theme[]>(`${this.environment.apiUrl}${this.userThemesPath}`)
      .pipe(tap((themes) => this.userThemesSubject.next(themes)))
      .subscribe();
  }

  subscribeToTheme(themeId: number): Observable<any> {
    return this.http
      .post(
        `${this.environment.apiUrl}${this.themesPath}/${themeId}/subscribe`,
        {}
      )
      .pipe(tap(() => this.getUserThemes()));
  }

  unsubscribeFromTheme(themeId: number): Observable<any> {
    return this.http
      .post(
        `${this.environment.apiUrl}${this.themesPath}/${themeId}/unsubscribe`,
        {}
      )
      .pipe(tap(() => this.getUserThemes()));
  }
}
