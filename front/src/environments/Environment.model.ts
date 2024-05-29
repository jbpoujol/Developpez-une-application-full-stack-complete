/**
 * Interface representing the environment configuration.
 */
export interface Environment {
  /** Indicates whether the application is running in production mode */
  production: boolean;
  /** The base URL of the API */
  apiUrl: string;
}
