import { Component, OnDestroy, OnInit } from '@angular/core';
import { ThemeService } from '../../services/themes.service';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.scss'],
})
export class ThemesListComponent implements OnInit, OnDestroy {
  themes$ = this.themeService.themes$;

  destroy$ = new Subject<void>();

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.themeService.getThemes();
  }

  onSubscribe(themeId: number): void {
    this.themeService
      .subscribeToTheme(themeId)
      .pipe(takeUntil(this.destroy$))
      .subscribe();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}