import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Theme } from '../models/Theme.model';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private themesUrl = 'http://localhost:8080/api/themes';

  private themesSubject = new BehaviorSubject<Theme[]>([]);

  themes$ = this.themesSubject.asObservable();

  constructor(private http: HttpClient) {}

  getThemes(): void {
    this.http
      .get<Theme[]>(this.themesUrl)
      .pipe(tap((themes) => this.themesSubject.next(themes)))
      .subscribe();
  }

  subscribeToTheme(themeId: number): Observable<any> {
    return this.http
      .post(`${this.themesUrl}/${themeId}/subscribe`, {})
      .pipe(tap(() => this.getThemes()));
  }

  unsubscribeFromTheme(themeId: number): Observable<any> {
    return this.http
      .post(`${this.themesUrl}/${themeId}/unsubscribe`, {})
      .pipe(tap(() => this.getThemes()));
  }
}
