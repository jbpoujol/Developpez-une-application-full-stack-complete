package com.openclassrooms.mddapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an article.
 * <p>
 * This class maps to a database entity for storing article information.
 * It includes details such as the article's title, content, creation date,
 * author, associated theme, and comments.
 */
@Data
@Entity
public class Article {
    /**
     * The unique identifier of the article.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the article.
     */
    private String title;

    /**
     * The content of the article.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * The date and time when the article was created.
     */
    private LocalDateTime createdAt;

    /**
     * The author of the article.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private DBUser author;

    /**
     * The theme associated with the article.
     */
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    /**
     * The list of comments associated with the article.
     */
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Article() {}

    /**
     * Constructs a new Article with the specified details.
     *
     * @param title     The title of the article.
     * @param content   The content of the article.
     * @param createdAt The date and time when the article was created.
     * @param author    The author of the article.
     * @param theme     The theme associated with the article.
     * @param comments  The list of comments associated with the article.
     */
    public Article(String title, String content, LocalDateTime createdAt, DBUser author, Theme theme, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.theme = theme;
        this.comments = comments != null ? comments : new ArrayList<>();
    }
}
