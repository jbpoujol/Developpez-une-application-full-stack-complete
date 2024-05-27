package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UpdateProfileRequestDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final AuthenticationService authenticationService;

    public ProfileController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<?> getCurrentUserDetails() {
        UserDTO userDTO = authenticationService.getCurrentUserDetails();
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateProfileRequestDTO updateRequestDTO) {
        UserDTO updatedUser = authenticationService.updateUserProfile(updateRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/themes")
    public ResponseEntity<?> getUserThemes() {
        List<ThemeDTO> userThemes = authenticationService.getCurrentUserThemes();
        return ResponseEntity.ok(userThemes);
    }
}
