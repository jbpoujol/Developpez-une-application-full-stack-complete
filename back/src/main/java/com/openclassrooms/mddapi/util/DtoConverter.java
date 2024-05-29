package com.openclassrooms.mddapi.util;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for converting entities to their corresponding DTOs.
 * <p>
 * This class provides static methods for converting various entities to their
 * Data Transfer Object (DTO) representations.
 */
public class DtoConverter {

    /**
     * Converts a {@link Theme} entity to a {@link ThemeDTO}.
     *
     * @param theme the Theme entity to convert
     * @return a ThemeDTO containing the theme details
     */
    public static ThemeDTO convertToThemeDTO(Theme theme) {
        return new ThemeDTO(theme.getId(), theme.getName(), theme.getDescription());
    }

    /**
     * Converts a {@link DBUser} entity to a {@link UserDTO}.
     *
     * @param user the DBUser entity to convert
     * @return a UserDTO containing the user details and subscribed themes
     */
    public static UserDTO convertToUserDTO(DBUser user) {
        List<ThemeDTO> subscribedThemes = user.getSubscribedThemes().stream()
                .map(DtoConverter::convertToThemeDTO)
                .collect(Collectors.toList());

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt(), subscribedThemes);
    }

    /**
     * Converts an {@link Article} entity to an {@link ArticleDTO}.
     *
     * @param article the Article entity to convert
     * @return an ArticleDTO containing the article details and comments
     * @throws IllegalArgumentException if the article is null
     */
    public static ArticleDTO convertToArticleDTO(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }

        List<CommentDTO> comments = article.getComments() != null ?
                article.getComments().stream()
                        .map(DtoConverter::convertToCommentDTO)
                        .collect(Collectors.toList()) : Collections.emptyList();

        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getAuthor() != null ? article.getAuthor().getName() : null,
                article.getTheme() != null ? article.getTheme().getName() : null,
                article.getTheme() != null ? article.getTheme().getId() : null,
                comments
        );
    }

    /**
     * Converts a {@link Comment} entity to a {@link CommentDTO}.
     *
     * @param comment the Comment entity to convert
     * @return a CommentDTO containing the comment details
     */
    public static CommentDTO convertToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getAuthor() != null ? comment.getAuthor().getName() : null
        );
    }
}
