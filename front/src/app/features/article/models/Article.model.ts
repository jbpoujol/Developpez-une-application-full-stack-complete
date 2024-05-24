import { Comment } from './';

export interface Article {
  id: number;
  title: string;
  content: string;
  createdAt: string;
  authorName: string;
  themeName: string;
  comments: Comment[];
}
