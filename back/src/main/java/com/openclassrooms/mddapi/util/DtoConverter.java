package com.openclassrooms.mddapi.util;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {

    public static ThemeDTO convertToThemeDTO(Theme theme) {
        return new ThemeDTO(theme.getId(), theme.getName(), theme.getDescription());
    }

    public static UserDTO convertToUserDTO(DBUser user) {
        List<ThemeDTO> subscribedThemes = user.getSubscribedThemes().stream()
                .map(DtoConverter::convertToThemeDTO)
                .collect(Collectors.toList());

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt() ,user.getUpdatedAt(), subscribedThemes);
    }

    public static ArticleDTO convertToArticleDTO(Article article) {
        List<CommentDTO> comments = article.getComments().stream()
                .map(DtoConverter::convertToCommentDTO)
                .collect(Collectors.toList());

        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getAuthor().getName(),
                article.getTheme().getName(),
                article.getTheme().getId(),
                comments

        );
    }

    public static CommentDTO convertToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getAuthor().getName()
        );
    }
}
