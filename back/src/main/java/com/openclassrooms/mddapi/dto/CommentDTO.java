package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Comment.
 */
@Data
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String authorName;

    /**
     * Default constructor.
     */
    public CommentDTO() {
    }

    /**
     * Constructs a CommentDTO with the specified details.
     *
     * @param id the comment ID
     * @param content the comment content
     * @param createdAt the creation time of the comment
     * @param authorName the name of the author
     */
    public CommentDTO(Long id, String content, LocalDateTime createdAt, String authorName) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.authorName = authorName;
    }
}
