import { Component, Input } from '@angular/core';
import { Comment } from '../../models';

/**
 * ArticleCommentComponent is responsible for displaying a comment.
 */
@Component({
  selector: 'app-article-comment',
  templateUrl: './article-comment.component.html',
  styleUrls: ['./article-comment.component.scss'],
})
export class ArticleCommentComponent {
  /** The comment to be displayed */
  @Input() comment!: Comment;
}
