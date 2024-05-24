import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ArticleService } from '../../services/article.service';
import { ThemeService } from 'src/app/features/themes/services/themes.service';
import { Theme } from '@shared';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.scss'],
})
export class AddArticleComponent implements OnInit {
  addArticleForm: FormGroup;
  themes: Theme[] = [];

  constructor(
    private fb: FormBuilder,
    private themeService: ThemeService,
    private articleService: ArticleService
  ) {
    this.addArticleForm = this.fb.group({
      themeId: [null, Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.themeService.themes$.subscribe((themes) => {
      this.themes = themes;
    });
    this.themeService.getThemes();
  }

  onSubmit(): void {
    if (this.addArticleForm.valid) {
      const { themeId, title, content } = this.addArticleForm.value;
      this.articleService.addArticle(title, content, themeId).subscribe(() => {
        this.addArticleForm.reset();
      });
    }
  }
}
