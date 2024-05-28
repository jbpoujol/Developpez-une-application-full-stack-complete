package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    private ArticleDTO articleDTO;
    private CommentDTO commentDTO;

    @BeforeEach
    public void setUp() {
        articleDTO = new ArticleDTO();
        articleDTO.setId(1L);
        articleDTO.setTitle("Test Article");
        articleDTO.setContent("Test Content");
        articleDTO.setThemeId(1L);

        commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setContent("Test Comment");
    }

    @Test
    public void testGetAllArticles() {
        when(articleService.getAllArticles()).thenReturn(Arrays.asList(articleDTO));

        ResponseEntity<List<ArticleDTO>> response = articleController.getAllArticles();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(articleService, times(1)).getAllArticles();
    }

    @Test
    public void testGetArticlesForSubscribedThemes() {
        when(articleService.getArticlesForSubscribedThemes()).thenReturn(Arrays.asList(articleDTO));

        ResponseEntity<List<ArticleDTO>> response = articleController.getArticlesForSubscribedThemes();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(articleService, times(1)).getArticlesForSubscribedThemes();
    }

    @Test
    public void testAddArticle() {
        when(articleService.addArticle(anyString(), anyString(), anyLong())).thenReturn(articleDTO);

        ResponseEntity<ArticleDTO> response = articleController.addArticle(articleDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(articleDTO, response.getBody());
        verify(articleService, times(1)).addArticle(anyString(), anyString(), anyLong());
    }

    @Test
    public void testGetArticle() {
        when(articleService.getArticle(1L)).thenReturn(articleDTO);

        ResponseEntity<ArticleDTO> response = articleController.getArticle(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(articleDTO, response.getBody());
        verify(articleService, times(1)).getArticle(1L);
    }

    @Test
    public void testAddComment() {
        when(articleService.addComment(anyLong(), anyString())).thenReturn(commentDTO);

        ResponseEntity<CommentDTO> response = articleController.addComment(1L, commentDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commentDTO, response.getBody());
        verify(articleService, times(1)).addComment(anyLong(), anyString());
    }
}
