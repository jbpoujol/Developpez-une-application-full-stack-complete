package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.model.Theme;

import java.util.List;

/**
 * Service interface for managing themes.
 * <p>
 * This interface provides methods for retrieving all themes, and for subscribing and unsubscribing to themes.
 */
public interface ThemeService {

    /**
     * Retrieves all themes.
     *
     * @return a list of {@link ThemeDTO} objects representing all themes.
     */
    List<ThemeDTO> getAllThemes();

    /**
     * Subscribes the current user to a specific theme.
     * <p>
     * This method allows the current authenticated user to subscribe to a theme
     * identified by the provided theme ID.
     *
     * @param themeId the ID of the theme to subscribe to.
     */
    void subscribeToTheme(Long themeId);

    /**
     * Unsubscribes the current user from a specific theme.
     * <p>
     * This method allows the current authenticated user to unsubscribe from a theme
     * identified by the provided theme ID.
     *
     * @param themeId the ID of the theme to unsubscribe from.
     */
    void unsubscribeFromTheme(Long themeId);
}
