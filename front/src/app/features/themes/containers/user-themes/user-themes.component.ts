import { Component, OnDestroy, OnInit } from '@angular/core';
import { ThemeService } from '../../services/themes.service';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-user-themes',
  templateUrl: './user-themes.component.html',
  styleUrls: ['./user-themes.component.scss'],
})
export class UserThemesComponent implements OnInit, OnDestroy {
  userThemes$ = this.themeService.userThemes$;

  destroy$ = new Subject<void>();

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.themeService.getUserThemes();
  }

  onUnsubscribe(themeId: number): void {
    this.themeService
      .unsubscribeFromTheme(themeId)
      .pipe(takeUntil(this.destroy$))
      .subscribe();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
