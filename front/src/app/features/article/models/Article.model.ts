import { Comment } from './';

/**
 * Interface representing the structure of an article.
 */
export interface Article {
  /** The unique identifier of the article */
  id: number;
  /** The title of the article */
  title: string;
  /** The content of the article */
  content: string;
  /** The creation date of the article in ISO format */
  createdAt: string;
  /** The name of the author of the article */
  authorName: string;
  /** The name of the theme associated with the article */
  themeName: string;
  /** The list of comments associated with the article */
  comments: Comment[];
}
