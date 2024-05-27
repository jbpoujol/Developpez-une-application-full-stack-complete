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

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginPath = '/auth/login';
  private registerPath = '/auth/register';
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
    private environment: EnvironmentService,
    private profileService: ProfileService
  ) {}

  init() {
    this.profileService.loadCurrentUser().subscribe((user) => {
      this.currentUserSubject.next(user);
    });
  }

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

  logout(): void {
    localStorage.removeItem('mdd-token');
    this.currentUserSubject.next(null);
    this.router.navigate(['login']);
  }

  private setToken(token: string): void {
    localStorage.setItem('mdd-token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('mdd-token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
