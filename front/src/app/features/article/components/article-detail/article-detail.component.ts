import { Component, Input } from '@angular/core';
import { Article } from '../../models';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss'],
})
export class ArticleDetailComponent {
  @Input() article!: Article;
}
