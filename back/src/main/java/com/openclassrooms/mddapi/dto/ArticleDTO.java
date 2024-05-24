package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    public ArticleDTO() {
    }

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
