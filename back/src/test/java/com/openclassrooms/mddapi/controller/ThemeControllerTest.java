package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.service.ThemeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ThemeControllerTest {

    @Mock
    private ThemeService themeService;

    @InjectMocks
    private ThemeController themeController;

    private List<ThemeDTO> themeDTOList;

    @BeforeEach
    public void setUp() {
        // Creating a sample theme list
        themeDTOList = new ArrayList<>();
        ThemeDTO themeDTO = new ThemeDTO();
        themeDTO.setId(1L);
        themeDTO.setName("Technology");
        themeDTOList.add(themeDTO);
    }

    @Test
    public void testGetAllThemes() {
        when(themeService.getAllThemes()).thenReturn(themeDTOList);

        ResponseEntity<List<ThemeDTO>> response = themeController.getAllThemes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(themeDTOList, response.getBody());
        verify(themeService, times(1)).getAllThemes();
    }

    @Test
    public void testSubscribeToTheme() {
        Long themeId = 1L;

        doNothing().when(themeService).subscribeToTheme(themeId);

        ResponseEntity<?> response = themeController.subscribeToTheme(themeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(themeService, times(1)).subscribeToTheme(themeId);
    }

    @Test
    public void testUnsubscribeFromTheme() {
        Long themeId = 1L;

        doNothing().when(themeService).unsubscribeFromTheme(themeId);

        ResponseEntity<?> response = themeController.unsubscribeFromTheme(themeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(themeService, times(1)).unsubscribeFromTheme(themeId);
    }
}
