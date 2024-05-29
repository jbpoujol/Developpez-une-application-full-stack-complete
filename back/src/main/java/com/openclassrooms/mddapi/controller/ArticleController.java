package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing articles and comments.
 */
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Retrieves all articles.
     *
     * @return a ResponseEntity containing a list of all ArticleDTOs
     */
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    /**
     * Retrieves articles for subscribed themes.
     *
     * @return a ResponseEntity containing a list of ArticleDTOs for subscribed themes
     */
    @GetMapping("/subscribed")
    public ResponseEntity<List<ArticleDTO>> getArticlesForSubscribedThemes() {
        List<ArticleDTO> articles = articleService.getArticlesForSubscribedThemes();
        return ResponseEntity.ok(articles);
    }

    /**
     * Adds a new article.
     *
     * @param articleDTO the article data to add
     * @return a ResponseEntity containing the added ArticleDTO
     */
    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO article = articleService.addArticle(articleDTO.getTitle(), articleDTO.getContent(), articleDTO.getThemeId());
        return ResponseEntity.ok(article);
    }

    /**
     * Retrieves a specific article by its ID.
     *
     * @param articleId the ID of the article to retrieve
     * @return a ResponseEntity containing the requested ArticleDTO
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long articleId) {
        ArticleDTO article = articleService.getArticle(articleId);
        return ResponseEntity.ok(article);
    }

    /**
     * Adds a comment to a specific article.
     *
     * @param articleId the ID of the article to add a comment to
     * @param commentDTO the comment data to add
     * @return a ResponseEntity containing the added CommentDTO
     */
    @PostMapping("/{articleId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long articleId, @RequestBody CommentDTO commentDTO) {
        CommentDTO comment = articleService.addComment(articleId, commentDTO.getContent());
        return ResponseEntity.ok(comment);
    }
}
