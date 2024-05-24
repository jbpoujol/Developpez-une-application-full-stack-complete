import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Article } from '../../models';

@Component({
  selector: 'app-article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.scss'],
})
export class ArticleCardComponent {
  @Input() article!: Article;
  @Output() articleClick = new EventEmitter<number>();

  onClick(): void {
    this.articleClick.emit(this.article.id);
  }
}
