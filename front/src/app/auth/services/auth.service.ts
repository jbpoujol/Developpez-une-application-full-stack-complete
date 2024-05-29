import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginRequest } from '../models/LoginRequest.model';
import { RegisterRequest } from '../models/RegisterRequest.model';
import { EnvironmentService } from '@core';
import { User } from '../models/User.model';
import { ProfileService } from '@profile';

/**
 * AuthService is responsible for handling authentication-related operations,
 * including login, registration, logout, and managing the current user state.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  /** API endpoint for login */
  private loginPath = '/auth/login';
  /** API endpoint for registration */
  private registerPath = '/auth/register';
  /** BehaviorSubject to hold the current user state */
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  /** Observable of the current user state */
  currentUser$ = this.currentUserSubject.asObservable();

  /**
   * Creates an instance of AuthService.
   * @param http - The HTTP client for making requests
   * @param router - The router for navigation
   * @param environment - The environment service for accessing environment variables
   * @param profileService - The profile service for loading user profile information
   */
  constructor(
    private http: HttpClient,
    private router: Router,
    private environment: EnvironmentService,
    private profileService: ProfileService
  ) {}

  /**
   * Initializes the AuthService by loading the current user profile.
   */
  init(): void {
    this.profileService.loadCurrentUser().subscribe((user) => {
      this.currentUserSubject.next(user);
    });
  }

  /**
   * Logs in a user.
   * @param loginRequest - The login request object containing email and password
   * @returns An Observable of the HTTP response
   */
  login(loginRequest: LoginRequest): Observable<any> {
    return this.http
      .post<any>(`${this.environment.apiUrl}${this.loginPath}`, loginRequest)
      .pipe(
        tap((response) => {
          this.setToken(response.token);
          this.profileService.loadCurrentUser().subscribe((user) => {
            this.currentUserSubject.next(user);
          });
        })
      );
  }

  /**
   * Registers a new user.
   * @param registerRequest - The registration request object containing name, email, and password
   * @returns An Observable of the HTTP response
   */
  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http
      .post<any>(
        `${this.environment.apiUrl}${this.registerPath}`,
        registerRequest
      )
      .pipe(
        tap((response) => {
          this.setToken(response.token);
          this.profileService.loadCurrentUser().subscribe((user) => {
            this.currentUserSubject.next(user);
          });
        })
      );
  }

  /**
   * Logs out the current user.
   */
  logout(): void {
    localStorage.removeItem('mdd-token');
    this.currentUserSubject.next(null);
    this.router.navigate(['login']);
  }

  /**
   * Sets the authentication token in local storage.
   * @param token - The authentication token
   */
  private setToken(token: string): void {
    localStorage.setItem('mdd-token', token);
  }

  /**
   * Retrieves the authentication token from local storage.
   * @returns The authentication token, or null if not found
   */
  getToken(): string | null {
    return localStorage.getItem('mdd-token');
  }

  /**
   * Checks if the user is authenticated.
   * @returns True if the user is authenticated, false otherwise
   */
  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
