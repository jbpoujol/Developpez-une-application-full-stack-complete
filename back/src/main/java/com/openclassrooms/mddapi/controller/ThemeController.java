package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.service.ThemeService;
import com.openclassrooms.mddapi.excepton.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        try {
            themeService.subscribeToTheme(themeId);
            return ResponseEntity.ok().build();
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{themeId}/unsubscribe")
    public ResponseEntity<?> unsubscribeFromTheme(@PathVariable Long themeId) {
        try {
            themeService.unsubscribeFromTheme(themeId);
            return ResponseEntity.ok().build();
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
}
