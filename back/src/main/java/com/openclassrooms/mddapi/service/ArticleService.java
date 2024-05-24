package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> getAllArticles();

    List<ArticleDTO> getArticlesForSubscribedThemes();

    public ArticleDTO addArticle(String title, String content, Long themeId);

    public ArticleDTO getArticle(Long articleId);

    public CommentDTO addComment(Long articleId, String content);
}
