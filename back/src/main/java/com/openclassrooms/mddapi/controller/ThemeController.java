package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.service.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<List<ThemeDTO>> getAllThemes() {
        List<ThemeDTO> themes = themeService.getAllThemes();
        return ResponseEntity.ok(themes);
    }

    @PostMapping("/{themeId}/subscribe")
    public ResponseEntity<?> subscribeToTheme(@PathVariable Long themeId) {
        themeService.subscribeToTheme(themeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{themeId}/unsubscribe")
    public ResponseEntity<?> unsubscribeFromTheme(@PathVariable Long themeId) {
            themeService.unsubscribeFromTheme(themeId);
            return ResponseEntity.ok().build();
    }
}
