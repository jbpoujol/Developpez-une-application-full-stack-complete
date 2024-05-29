package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for Article.
 */
@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String authorName;
    private String themeName;
    private Long themeId;
    private List<CommentDTO> comments;

    /**
     * Default constructor.
     */
    public ArticleDTO() {
    }

    /**
     * Constructs an ArticleDTO with the specified details.
     *
     * @param id the article ID
     * @param title the article title
     * @param content the article content
     * @param createdAt the creation time of the article
     * @param authorName the name of the author
     * @param themeName the name of the theme
     * @param themeId the ID of the theme
     * @param comments the list of comments associated with the article
     */
    public ArticleDTO(Long id, String title, String content, LocalDateTime createdAt, String authorName, String themeName, Long themeId, List<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.authorName = authorName;
        this.themeName = themeName;
        this.themeId = themeId;
        this.comments = comments;
    }
}
