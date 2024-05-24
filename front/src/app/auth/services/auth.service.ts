import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../models/User.model';
import { LoginRequest } from '../models/LoginRequest.model';
import { RegisterRequest } from '../models/RegisterRequest.model';
import { UpdateProfileRequest } from '../models/UpdateProfileRequest.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  private currentUserSubject = new BehaviorSubject<User | null>(null);

  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  init() {
    this.loadCurrentUser();
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginRequest).pipe(
      tap((response) => {
        this.setToken(response.token);
        this.loadCurrentUser();
      })
    );
  }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, registerRequest).pipe(
      tap((response) => {
        this.setToken(response.token);
        this.loadCurrentUser();
      })
    );
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/profile`);
  }

  updateProfile(updateProfileRequest: UpdateProfileRequest): Observable<any> {
    return this.http
      .put<any>(`${this.apiUrl}/profile`, updateProfileRequest)
      .pipe(
        tap(() => {
          this.loadCurrentUser();
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

  private loadCurrentUser(): void {
    const token = this.getToken();
    if (token) {
      this.getCurrentUser().subscribe((user) =>
        this.currentUserSubject.next(user)
      );
    }
  }
}
