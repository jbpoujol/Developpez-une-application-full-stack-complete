/**
 * Interface representing the structure of a comment.
 */
export interface Comment {
  /** The unique identifier of the comment */
  id: number;
  /** The content of the comment */
  content: string;
  /** The creation date of the comment in ISO format */
  createdAt: string;
  /** The name of the author of the comment */
  authorName: string;
}
