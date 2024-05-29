package com.openclassrooms.mddapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a comment.
 * <p>
 * This class maps to a database entity for storing comment information.
 * It includes details such as the comment's content, creation date,
 * author, and the associated article.
 */
@Data
@Entity
public class Comment {
    /**
     * The unique identifier of the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The content of the comment.
     */
    private String content;

    /**
     * The date and time when the comment was created.
     */
    private LocalDateTime createdAt;

    /**
     * The author of the comment.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private DBUser author;

    /**
     * The article associated with the comment.
     */
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // Default constructor
    public Comment() {}

    // Constructor with arguments
    public Comment(String content, LocalDateTime createdAt, DBUser author, Article article) {
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.article = article;
    }
}

