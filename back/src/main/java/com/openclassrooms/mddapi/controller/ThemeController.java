package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.service.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing themes.
 */
@RestController
@RequestMapping("/api/v1/themes")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    /**
     * Retrieves all themes.
     *
     * @return a ResponseEntity containing a list of all ThemeDTOs
     */
    @GetMapping
    public ResponseEntity<List<ThemeDTO>> getAllThemes() {
        List<ThemeDTO> themes = themeService.getAllThemes();
        return ResponseEntity.ok(themes);
    }

    /**
     * Subscribes the current user to a specific theme.
     *
     * @param themeId the ID of the theme to subscribe to
     * @return a ResponseEntity with an OK status
     */
    @PostMapping("/{themeId}/subscribe")
    public ResponseEntity<?> subscribeToTheme(@PathVariable Long themeId) {
        themeService.subscribeToTheme(themeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Unsubscribes the current user from a specific theme.
     *
     * @param themeId the ID of the theme to unsubscribe from
     * @return a ResponseEntity with an OK status
     */
    @PostMapping("/{themeId}/unsubscribe")
    public ResponseEntity<?> unsubscribeFromTheme(@PathVariable Long themeId) {
        themeService.unsubscribeFromTheme(themeId);
        return ResponseEntity.ok().build();
    }
}
