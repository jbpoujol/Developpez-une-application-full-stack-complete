import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { EnvironmentService } from '@core';
import { User } from '@auth';
import { UpdateProfileRequest } from '../models';

/**
 * ProfileService is responsible for managing user profile operations.
 * It provides methods to get the current user profile, update the profile, and load the current user.
 */
@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  /** Path to the profile API endpoint */
  private profilePath = '/profile';

  /**
   * Creates an instance of ProfileService.
   * @param http - The HTTP client for making requests
   * @param environment - The environment service for accessing environment variables
   */
  constructor(
    private http: HttpClient,
    private environment: EnvironmentService
  ) {}

  /**
   * Fetches the current user profile.
   * @returns An Observable of the current user
   */
  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.environment.apiUrl}${this.profilePath}`);
  }

  /**
   * Updates the user profile.
   * @param updateProfileRequest - The request object containing updated profile information
   * @returns An Observable of the HTTP response
   */
  updateProfile(updateProfileRequest: UpdateProfileRequest): Observable<any> {
    return this.http.put<any>(
      `${this.environment.apiUrl}${this.profilePath}`,
      updateProfileRequest
    );
  }

  /**
   * Loads the current user profile if a token is present in local storage.
   * @returns An Observable of the current user or null if no token is found
   */
  loadCurrentUser(): Observable<User | null> {
    const token = localStorage.getItem('mdd-token');
    if (token) {
      return this.getCurrentUser();
    } else {
      return of(null);
    }
  }
}
