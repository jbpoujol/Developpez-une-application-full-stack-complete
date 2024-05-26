import { Component, OnInit } from '@angular/core';
import { Article } from '../../models';
import { ArticleService } from '../../services/article.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-articles-list',
  templateUrl: './articles-list.component.html',
  styleUrls: ['./articles-list.component.scss'],
})
export class ArticlesListComponent implements OnInit {
  subscribedArticles$ = this.articleService.subscribedArticles$;

  constructor(
    private articleService: ArticleService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.articleService.getSubscribedArticles();
  }

  onArticleClick(articleId: number): void {
    this.router.navigateByUrl(`/articles/details/${articleId}`);
  }
}
