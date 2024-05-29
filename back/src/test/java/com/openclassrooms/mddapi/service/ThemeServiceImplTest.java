package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.exception.CustomNotFoundException;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.service.impl.ThemeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ThemeServiceImplTest {

    @Mock
    private ThemeRepository themeRepository;

    @Mock
    private DBUserRepository userRepository;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private ThemeServiceImpl themeService;

    private DBUser mockUser;
    private Theme mockTheme;

    @BeforeEach
    public void setUp() {
        mockUser = new DBUser();
        mockUser.setEmail("test@example.com");
        mockUser.setSubscribedThemes(new ArrayList<>());  // Use modifiable list

        mockTheme = new Theme();
        mockTheme.setId(1L);
        mockTheme.setName("Test Theme");
    }

    @Test
    public void testGetAllThemes() {
        when(themeRepository.findAll()).thenReturn(List.of(mockTheme));
        List<ThemeDTO> themeDTOs = themeService.getAllThemes();
        assertEquals(1, themeDTOs.size());
        assertEquals("Test Theme", themeDTOs.get(0).getName());
    }

    @Test
    public void testSubscribeToTheme() {
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(themeRepository.findById(1L)).thenReturn(Optional.of(mockTheme));

        themeService.subscribeToTheme(1L);

        verify(userRepository).save(mockUser);
        assertEquals(1, mockUser.getSubscribedThemes().size());
    }

    @Test
    public void testSubscribeToTheme_ThemeNotFound() {
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(themeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> themeService.subscribeToTheme(1L));
    }

    @Test
    public void testUnsubscribeFromTheme() {
        mockUser.setSubscribedThemes(new ArrayList<>(List.of(mockTheme)));  // Use modifiable list
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(themeRepository.findById(1L)).thenReturn(Optional.of(mockTheme));

        themeService.unsubscribeFromTheme(1L);

        verify(userRepository).save(mockUser);
        assertEquals(0, mockUser.getSubscribedThemes().size());
    }

    @Test
    public void testUnsubscribeFromTheme_ThemeNotFound() {
        when(authenticationService.getCurrentAuthenticatedUser()).thenReturn(mockUser);
        when(themeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> themeService.unsubscribeFromTheme(1L));
    }
}
