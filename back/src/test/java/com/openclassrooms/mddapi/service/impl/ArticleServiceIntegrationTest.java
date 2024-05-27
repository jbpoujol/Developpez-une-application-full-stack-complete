package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleServiceIntegrationTest {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DBUserRepository dbUserRepository;

    @Autowired
    private ThemeRepository themeRepository;

    private DBUser mockUser;
    private Theme mockTheme;
    private Article mockArticle;

    @BeforeEach
    @Transactional
    public void setUp() {
        mockUser = new DBUser();
        mockUser.setName("integrationUser");
        mockUser.setEmail("integrationUser-" + UUID.randomUUID() + "@example.com"); // Ensure email is unique
        mockUser.setPassword("password"); // Ensure password is set
        dbUserRepository.save(mockUser);

        mockTheme = new Theme();
        mockTheme.setName("Integration Theme");
        themeRepository.save(mockTheme);

        mockArticle = new Article();
        mockArticle.setTitle("Integration Title");
        mockArticle.setContent("Integration Content");
        mockArticle.setCreatedAt(LocalDateTime.now());
        mockArticle.setAuthor(mockUser);
        mockArticle.setTheme(mockTheme);
        articleRepository.save(mockArticle);
    }

    @Test
    @Transactional
    public void testGetAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        assertNotNull(articles);
        assertFalse(articles.isEmpty());
    }

    @Test
    @Transactional
    public void testAddArticle() {
        ArticleDTO articleDTO = articleService.addArticle("New Integration Title", "New Integration Content", mockTheme.getId());
        assertNotNull(articleDTO);
        assertEquals("New Integration Title", articleDTO.getTitle());
    }

    @Test
    @Transactional
    public void testGetArticle() {
        ArticleDTO articleDTO = articleService.getArticle(mockArticle.getId());
        assertNotNull(articleDTO);
        assertEquals("Integration Title", articleDTO.getTitle());
    }

    @Test
    @Transactional
    public void testAddComment() {
        CommentDTO commentDTO = articleService.addComment(mockArticle.getId(), "Integration Comment");
        assertNotNull(commentDTO);
        assertEquals("Integration Comment", commentDTO.getContent());
    }
}
