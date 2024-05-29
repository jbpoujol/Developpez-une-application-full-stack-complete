import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Theme } from '@shared';
import { EnvironmentService } from '@core';

/**
 * ThemeService is responsible for managing themes.
 * It provides methods to fetch all themes, user themes, and to subscribe/unsubscribe to/from themes.
 */
@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  /** Path to the themes API endpoint */
  private themesPath = '/themes';
  /** Path to the user themes API endpoint */
  private userThemesPath = '/profile/themes';

  /** BehaviorSubject to hold the list of all themes */
  private themesSubject = new BehaviorSubject<Theme[]>([]);
  /** BehaviorSubject to hold the list of user themes */
  private userThemesSubject = new BehaviorSubject<Theme[]>([]);

  /** Observable of the list of all themes */
  themes$ = this.themesSubject.asObservable();
  /** Observable of the list of user themes */
  userThemes$ = this.userThemesSubject.asObservable();

  /**
   * Creates an instance of ThemeService.
   * @param http - The HTTP client for making requests
   * @param environment - The environment service for accessing environment variables
   */
  constructor(
    private http: HttpClient,
    private environment: EnvironmentService
  ) {}

  /**
   * Fetches all themes and updates the themesSubject.
   */
  getThemes(): void {
    this.http
      .get<Theme[]>(`${this.environment.apiUrl}${this.themesPath}`)
      .pipe(tap((themes) => this.themesSubject.next(themes)))
      .subscribe();
  }

  /**
   * Fetches the user themes and updates the userThemesSubject.
   */
  getUserThemes(): void {
    this.http
      .get<Theme[]>(`${this.environment.apiUrl}${this.userThemesPath}`)
      .pipe(tap((themes) => this.userThemesSubject.next(themes)))
      .subscribe();
  }

  /**
   * Subscribes to a theme.
   * @param themeId - The ID of the theme to subscribe to
   * @returns An Observable of the HTTP response
   */
  subscribeToTheme(themeId: number): Observable<any> {
    return this.http
      .post(
        `${this.environment.apiUrl}${this.themesPath}/${themeId}/subscribe`,
        {}
      )
      .pipe(tap(() => this.getUserThemes()));
  }

  /**
   * Unsubscribes from a theme.
   * @param themeId - The ID of the theme to unsubscribe from
   * @returns An Observable of the HTTP response
   */
  unsubscribeFromTheme(themeId: number): Observable<any> {
    return this.http
      .post(
        `${this.environment.apiUrl}${this.themesPath}/${themeId}/unsubscribe`,
        {}
      )
      .pipe(tap(() => this.getUserThemes()));
  }
}
