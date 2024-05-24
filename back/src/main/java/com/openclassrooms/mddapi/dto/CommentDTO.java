package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String authorName;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String content, LocalDateTime createdAt, String authorName) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.authorName = authorName;
    }
}
