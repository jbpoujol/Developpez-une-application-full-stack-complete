package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;

import java.util.List;

/**
 * Service interface for managing articles and comments.
 * <p>
 * This interface provides methods for retrieving, adding, and managing articles and their comments.
 */
public interface ArticleService {

    /**
     * Retrieves all articles.
     *
     * @return a list of ArticleDTO objects representing all articles
     */
    List<ArticleDTO> getAllArticles();

    /**
     * Retrieves articles for themes that the current user is subscribed to.
     *
     * @return a list of ArticleDTO objects representing articles for subscribed themes
     */
    List<ArticleDTO> getArticlesForSubscribedThemes();

    /**
     * Adds a new article.
     *
     * @param title   the title of the article
     * @param content the content of the article
     * @param themeId the ID of the theme associated with the article
     * @return an ArticleDTO object representing the added article
     */
    public ArticleDTO addArticle(String title, String content, Long themeId);

    /**
     * Retrieves a specific article by its ID.
     *
     * @param articleId the ID of the article to retrieve
     * @return an ArticleDTO object representing the retrieved article
     */
    public ArticleDTO getArticle(Long articleId);

    /**
     * Adds a comment to a specific article.
     *
     * @param articleId the ID of the article to add a comment to
     * @param content   the content of the comment
     * @return a CommentDTO object representing the added comment
     */
    public CommentDTO addComment(Long articleId, String content);
}
