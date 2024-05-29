import { Component, Input } from '@angular/core';
import { Article } from '../../models';

/**
 * ArticleDetailComponent is responsible for displaying detailed information about an article.
 */
@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss'],
})
export class ArticleDetailComponent {
  /** The article to be displayed in detail */
  @Input() article!: Article;
}
