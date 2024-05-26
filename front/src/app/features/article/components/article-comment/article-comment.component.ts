import { Component, Input } from '@angular/core';
import { Comment } from '../../models';

@Component({
  selector: 'app-article-comment',
  templateUrl: './article-comment.component.html',
  styleUrls: ['./article-comment.component.scss'],
})
export class ArticleCommentComponent {
  @Input() comment!: Comment;
}
