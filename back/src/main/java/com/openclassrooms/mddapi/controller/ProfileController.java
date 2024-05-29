package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UpdateProfileRequestDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing user profiles.
 */
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final AuthenticationService authenticationService;

    public ProfileController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Retrieves the current user's details.
     *
     * @return a ResponseEntity containing the current user's UserDTO
     */
    @GetMapping
    public ResponseEntity<?> getCurrentUserDetails() {
        UserDTO userDTO = authenticationService.getCurrentUserDetails();
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Updates the current user's profile.
     *
     * @param updateRequestDTO the updated profile data
     * @return a ResponseEntity containing the updated UserDTO
     */
    @PutMapping
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateProfileRequestDTO updateRequestDTO) {
        UserDTO updatedUser = authenticationService.updateUserProfile(updateRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Retrieves the themes the current user is subscribed to.
     *
     * @return a ResponseEntity containing a list of ThemeDTOs
     */
    @GetMapping("/themes")
    public ResponseEntity<?> getUserThemes() {
        List<ThemeDTO> userThemes = authenticationService.getCurrentUserThemes();
        return ResponseEntity.ok(userThemes);
    }
}
