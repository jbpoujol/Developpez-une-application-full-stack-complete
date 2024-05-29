import { Component, OnDestroy, OnInit } from '@angular/core';
import { ThemeService } from '../../services/themes.service';
import { Subject, takeUntil } from 'rxjs';
import { MessageService } from 'primeng/api';

/**
 * ThemesListComponent is responsible for displaying a list of themes.
 * It allows the user to subscribe to a theme and displays notifications using PrimeNG toast.
 */
@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.scss'],
})
export class ThemesListComponent implements OnInit, OnDestroy {
  /** Observable of the list of themes */
  themes$ = this.themeService.themes$;

  /** Subject used to unsubscribe from observables when the component is destroyed */
  private destroy$ = new Subject<void>();

  /**
   * Creates an instance of ThemesListComponent.
   * @param themeService - The service used to fetch and subscribe to themes
   * @param messageService - The message service used to display toast notifications
   */
  constructor(
    private themeService: ThemeService,
    private messageService: MessageService
  ) {}

  /**
   * Initializes the component by fetching the list of themes.
   */
  ngOnInit(): void {
    this.themeService.getThemes();
  }

  /**
   * Handles the subscription to a theme.
   * Displays a success toast notification on success and an error toast notification on failure.
   * @param themeId - The ID of the theme to subscribe to
   */
  onSubscribe(themeId: number): void {
    this.themeService
      .subscribeToTheme(themeId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Subscribed',
            detail: 'Successfully subscribed to theme',
          });
        },
        error: (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Failed to subscribe to theme',
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
