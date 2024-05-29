import { Component, OnDestroy, OnInit } from '@angular/core';
import { ThemeService } from '../../services/themes.service';
import { Subject, takeUntil } from 'rxjs';
import { MessageService } from 'primeng/api';

/**
 * UserThemesComponent is responsible for displaying the user's subscribed themes.
 * It allows the user to unsubscribe from a theme and displays notifications using PrimeNG toast.
 */
@Component({
  selector: 'app-user-themes',
  templateUrl: './user-themes.component.html',
  styleUrls: ['./user-themes.component.scss'],
})
export class UserThemesComponent implements OnInit, OnDestroy {
  /** Observable of the list of user themes */
  userThemes$ = this.themeService.userThemes$;

  /** Subject used to unsubscribe from observables when the component is destroyed */
  private destroy$ = new Subject<void>();

  /**
   * Creates an instance of UserThemesComponent.
   * @param themeService - The service used to fetch and manage user themes
   * @param messageService - The message service used to display toast notifications
   */
  constructor(
    private themeService: ThemeService,
    private messageService: MessageService
  ) {}

  /**
   * Initializes the component by fetching the list of user themes.
   */
  ngOnInit(): void {
    this.themeService.getUserThemes();
  }

  /**
   * Handles the unsubscription from a theme.
   * Displays a success toast notification on success and an error toast notification on failure.
   * @param themeId - The ID of the theme to unsubscribe from
   */
  onUnsubscribe(themeId: number): void {
    this.themeService
      .unsubscribeFromTheme(themeId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Unsubscribed',
            detail: 'Successfully unsubscribed from theme',
          });
        },
        error: (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Failed to unsubscribe from theme',
          });
        },
      });
  }

  /**
   * Cleans up the component by unsubscribing from observables.
   */
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
