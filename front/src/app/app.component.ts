import { Component, OnInit } from '@angular/core';
import { Layouts } from './layout/enums/Layouts.enum';
import { Router, RoutesRecognized } from '@angular/router';
import { AuthService } from './auth/services/auth.service';

/**
 * AppComponent is the root component of the application.
 * It handles the layout configuration and initializes the authentication service.
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  /** Enum of available layouts */
  Layouts = Layouts;

  /** The current layout of the application */
  layout!: Layouts;

  /**
   * Creates an instance of AppComponent.
   * @param router - The router for navigation and route events
   * @param authService - The authentication service for initialization
   */
  constructor(private router: Router, private authService: AuthService) {
    // Subscribe to router events to determine the layout based on route data
    this.router.events.subscribe((data) => {
      if (data instanceof RoutesRecognized) {
        this.layout = data?.state?.root?.firstChild?.data?.['layout'];
      }
    });
  }

  /**
   * Initializes the component by calling the init method of the AuthService.
   */
  ngOnInit(): void {
    this.authService.init();
  }
}
