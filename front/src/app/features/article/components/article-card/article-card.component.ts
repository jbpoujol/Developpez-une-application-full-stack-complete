import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Article } from '../../models';

/**
 * ArticleCardComponent is responsible for displaying an article card.
 * It emits an event when the article card is clicked.
 */
@Component({
  selector: 'app-article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.scss'],
})
export class ArticleCardComponent {
  /** The article to be displayed in the card */
  @Input() article!: Article;
  /** Event emitter that emits the article's ID when the card is clicked */
  @Output() articleClick = new EventEmitter<number>();

  /**
   * Handles the click event on the article card.
   * Emits the article's ID through the articleClick event emitter.
   */
  onClick(): void {
    this.articleClick.emit(this.article.id);
  }
}
