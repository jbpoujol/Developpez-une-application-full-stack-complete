import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { EnvironmentService } from '@core';
import { User } from '@auth';
import { UpdateProfileRequest } from '../models';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private profilePath = '/profile';

  constructor(
    private http: HttpClient,
    private environment: EnvironmentService
  ) {}

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.environment.apiUrl}${this.profilePath}`);
  }

  updateProfile(updateProfileRequest: UpdateProfileRequest): Observable<any> {
    return this.http.put<any>(
      `${this.environment.apiUrl}${this.profilePath}`,
      updateProfileRequest
    );
  }

  loadCurrentUser(): Observable<User | null> {
    const token = localStorage.getItem('mdd-token');
    if (token) {
      return this.getCurrentUser();
    } else {
      return of(null);
    }
  }
}
