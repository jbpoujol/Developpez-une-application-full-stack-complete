import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { AuthService } from '../services/auth.service';

/**
 * AuthGuard is a route guard that ensures routes are only accessible to authenticated users.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  /**
   * Creates an instance of AuthGuard.
   * @param authService - The service used to check authentication status
   * @param router - The router used for navigation
   */
  constructor(private authService: AuthService, private router: Router) {}

  /**
   * Determines whether the route can be activated.
   * If the user is authenticated, allows access to the route.
   * Otherwise, redirects to the login page.
   * @param next - The next route snapshot
   * @param state - The current router state snapshot
   * @returns True if the user is authenticated, false otherwise
   */
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
