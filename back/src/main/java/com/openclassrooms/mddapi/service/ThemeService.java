package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.model.Theme;

import java.util.List;

public interface ThemeService {

    List<ThemeDTO> getAllThemes();

    void subscribeToTheme(Long themeId) ;

    void unsubscribeFromTheme(Long themeId) ;
}
