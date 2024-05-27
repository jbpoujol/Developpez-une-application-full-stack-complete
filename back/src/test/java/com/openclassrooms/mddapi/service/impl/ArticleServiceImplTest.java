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
import com.openclassrooms.mddapi.service.AuthenticationService;
import com.openclassrooms.mddapi.util.DtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private DBUserRepository dbUserRepository;

    @Mock
    private ThemeRepository themeRepository;

    @Mock
    private AuthenticationService authenticationService;

    private DBUser mockUser;
    private Theme mockTheme;
    private Article mockArticle;

    @BeforeEach
    public void setUp() {
        mockUser = new DBUser();
        mockUser.setId(1L);
        mockUser.setName("testuser");

        mockTheme = new Theme();
        mockTheme.setId(1L);
        mockTheme.setName("Test Theme");

        mockArticle = new Article();
        mockArticle.setId(1L);
        mockArticle.setTitle("Test Title");
        mockArticle.setContent("Test Content");
        mockArticle.setTheme(mockTheme);
        mockArticle.setAuthor(mockUser);
        mockArticle.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testGetAllArticles() {
        when(articleRepository.findAll()).thenReturn(List.of(mockArticle));
        List<ArticleDTO> articles = articleService.getAllArticles();
        assertNotNull(articles);
        assertEquals(1, articles.size());
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    public void testGetArticlesForSubscribedThemes() {
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(articleRepository.findAll()).thenReturn(List.of(mockArticle));
        mockUser.setSubscribedThemes(List.of(mockTheme));

        List<ArticleDTO> articles = articleService.getArticlesForSubscribedThemes();
        assertNotNull(articles);
        assertEquals(1, articles.size());
    }

    @Test
    public void testAddArticle() {
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(themeRepository.findById(any(Long.class))).thenReturn(Optional.of(mockTheme));

        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        when(articleRepository.save(articleCaptor.capture())).thenAnswer(invocation -> {
            Article article = invocation.getArgument(0);
            article.setId(1L); // simulate setting ID after saving
            return article;
        });

        ArticleDTO articleDTO = articleService.addArticle("New Title", "New Content", 1L);
        assertNotNull(articleDTO);
        assertEquals("New Title", articleDTO.getTitle());

        Article savedArticle = articleCaptor.getValue();
        assertEquals("New Title", savedArticle.getTitle());
        assertEquals("New Content", savedArticle.getContent());
        assertEquals(mockTheme, savedArticle.getTheme());
        assertEquals(mockUser, savedArticle.getAuthor());

        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    public void testGetArticle() {
        when(articleRepository.findById(1L)).thenReturn(Optional.of(mockArticle));
        ArticleDTO articleDTO = articleService.getArticle(1L);
        assertNotNull(articleDTO);
        assertEquals("Test Title", articleDTO.getTitle());
        verify(articleRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddComment() {
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(mockArticle));
        Comment mockComment = new Comment();
        mockComment.setContent("Test Comment");
        mockComment.setArticle(mockArticle);
        mockComment.setAuthor(mockUser);
        when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);

        CommentDTO commentDTO = articleService.addComment(1L, "Test Comment");
        assertNotNull(commentDTO);
        assertEquals("Test Comment", commentDTO.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }
}
