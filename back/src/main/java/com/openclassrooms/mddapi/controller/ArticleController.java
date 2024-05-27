package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/subscribed")
    public ResponseEntity<List<ArticleDTO>> getArticlesForSubscribedThemes() {
        List<ArticleDTO> articles = articleService.getArticlesForSubscribedThemes();
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO article = articleService.addArticle(articleDTO.getTitle(), articleDTO.getContent(), articleDTO.getThemeId());
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long articleId) {
        ArticleDTO article = articleService.getArticle(articleId);
        return ResponseEntity.ok(article);
    }

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long articleId, @RequestBody CommentDTO commentDTO) {
        CommentDTO comment = articleService.addComment(articleId, commentDTO.getContent());
        return ResponseEntity.ok(comment);
    }
}
