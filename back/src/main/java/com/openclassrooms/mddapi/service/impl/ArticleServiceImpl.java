package com.openclassrooms.mddapi.service.impl;
import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.AuthenticationService;
import com.openclassrooms.mddapi.util.DtoConverter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final DBUserRepository dbUserRepository;
    private final ThemeRepository themeRepository;
    private final AuthenticationService authenticationService;

    public ArticleServiceImpl(ArticleRepository articleRepository, CommentRepository commentRepository, DBUserRepository dbUserRepository, ThemeRepository themeRepository, AuthenticationService authenticationService) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.dbUserRepository = dbUserRepository;
        this.themeRepository = themeRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(DtoConverter::convertToArticleDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<ArticleDTO> getArticlesForSubscribedThemes() {
        DBUser currentUser = authenticationService.getCurrentAuthenticatedUser();
        if (currentUser == null) {
            return Collections.emptyList();
        }
        List<Theme> subscribedThemes = currentUser.getSubscribedThemes();
        List<Long> subscribedThemeIds = subscribedThemes.stream()
                .map(Theme::getId)
                .collect(Collectors.toList());
        return articleRepository.findAll().stream()
                .filter(article -> {
                    return subscribedThemeIds.contains(article.getTheme().getId());
                })
                .map(DtoConverter::convertToArticleDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ArticleDTO addArticle(String title, String content, Long themeId) {
        DBUser currentUser = authenticationService.getCurrentAuthenticatedUser();
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("Theme not found"));

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreatedAt(LocalDateTime.now());
        article.setAuthor(currentUser);
        article.setTheme(theme);

        Article savedArticle = articleRepository.save(article);
        return DtoConverter.convertToArticleDTO(savedArticle);
    }

    @Override
    public ArticleDTO getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));
        return DtoConverter.convertToArticleDTO(article);
    }

    @Override
    @Transactional
    public CommentDTO addComment(Long articleId, String content) {
        DBUser currentUser = authenticationService.getCurrentAuthenticatedUser();
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setAuthor(currentUser);
        comment.setArticle(article);

        Comment savedComment = commentRepository.save(comment);
        return DtoConverter.convertToCommentDTO(savedComment);
    }
}
