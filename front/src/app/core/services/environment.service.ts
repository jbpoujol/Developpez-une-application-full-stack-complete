import { Injectable } from '@angular/core';
import { Environment, environment } from '@environment';

/**
 * EnvironmentService provides access to environment-specific variables.
 */
@Injectable({
  providedIn: 'root',
})
export class EnvironmentService implements Environment {
  /**
   * Returns whether the application is running in production mode.
   * @returns True if the application is in production mode, false otherwise.
   */
  get production(): boolean {
    return environment.production;
  }

  /**
   * Returns the API URL for the application.
   * @returns The API URL as a string.
   */
  get apiUrl(): string {
    return environment.apiUrl;
  }
}
