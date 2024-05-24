import { Component, OnInit } from '@angular/core';
import { ThemeService } from '../../services/themes.service';
import { Theme } from '../../models/Theme.model';

@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.scss'],
})
export class ThemesListComponent implements OnInit {
  themes$ = this.themeService.themes$;

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.themeService.getThemes();
  }

  onSubscribe(themeId: number): void {
    this.themeService.subscribeToTheme(themeId).subscribe();
  }
}
