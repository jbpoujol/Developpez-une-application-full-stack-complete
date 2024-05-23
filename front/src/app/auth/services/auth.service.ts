import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { email, password });
  }

  register(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, { email, password });
  }

  getProfile(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/profile`);
  }

  updateProfile(email: string, password: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/profile`, { email, password });
  }

  logout(): void {
    localStorage.removeItem('mdd-token');
    this.router.navigate(['login']);
  }

  setToken(token: string): void {
    localStorage.setItem('mdd-token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('mdd-token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
