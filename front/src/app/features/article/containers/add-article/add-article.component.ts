import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ArticleService } from '../../services/article.service';
import { ThemeService } from 'src/app/features/themes/services/themes.service';
import { Theme } from '@shared';

/**
 * AddArticleComponent is responsible for providing a form to add a new article.
 * It fetches available themes and handles the submission of the article.
 */
@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.scss'],
})
export class AddArticleComponent implements OnInit {
  /** The form group for adding a new article */
  addArticleForm: FormGroup;
  /** The list of available themes */
  themes: Theme[] = [];

  /**
   * Creates an instance of AddArticleComponent.
   * @param fb - The form builder used to create the form group
   * @param themeService - The service used to fetch themes
   * @param articleService - The service used to add a new article
   */
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

  /**
   * Initializes the component by fetching the available themes.
   */
  ngOnInit(): void {
    this.themeService.themes$.subscribe((themes) => {
      this.themes = themes;
    });
    this.themeService.getThemes();
  }

  /**
   * Handles the form submission.
   * If the form is valid, adds the article and resets the form.
   */
  onSubmit(): void {
    if (this.addArticleForm.valid) {
      const { themeId, title, content } = this.addArticleForm.value;
      this.articleService.addArticle(title, content, themeId).subscribe(() => {
        this.addArticleForm.reset();
      });
    }
  }
}
