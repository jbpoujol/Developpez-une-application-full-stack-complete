import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

/**
 * AuthInterceptor is an HTTP interceptor that adds an Authorization header
 * to outgoing requests, except for login and registration requests.
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  /**
   * Creates an instance of AuthInterceptor.
   * @param authService - The service used to get the authentication token
   */
  constructor(private authService: AuthService) {}

  /**
   * Intercepts HTTP requests to add an Authorization header with the authentication token.
   * @param request - The outgoing HTTP request
   * @param next - The HTTP handler to process the request
   * @returns An Observable of the HTTP event
   */
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const url = request.url;
    // Skip adding the Authorization header for login and registration requests
    if (!url.includes('/login') && !url.includes('/register')) {
      const token = this.authService.getToken();
      if (token) {
        // Clone the request to add the new header
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
        });
      }
    }
    // Pass the request to the next handler
    return next.handle(request);
  }
}
