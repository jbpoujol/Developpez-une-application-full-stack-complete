package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UpdateProfileRequestDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.excepton.CustomAlreadyExistsException;
import com.openclassrooms.mddapi.excepton.CustomAuthenticationException;
import com.openclassrooms.mddapi.excepton.CustomNotFoundException;
import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.RegistrationRequest;
import com.openclassrooms.mddapi.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint for user registration.
     * Takes a RegistrationRequest object containing user details and registers a new user.
     * Upon successful registration, returns a JWT token for the user.
     *
     * @param registrationRequest the registration request containing user details
     * @return ResponseEntity with JWT token
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            Map<String, String> tokenResponse = authenticationService.registerUserAndGenerateToken(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
        } catch (CustomAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Could not register the user. Please try again later."));
        }
    }

    /**
     * Endpoint for user login.
     * Authenticates the user with provided credentials and generates a JWT token upon successful authentication.
     *
     * @param loginRequest the login request containing user credentials
     * @return ResponseEntity with a map containing the JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Map<String, String> tokenResponse = authenticationService.authenticateAndGenerateToken(loginRequest);
            return ResponseEntity.ok(tokenResponse);
        } catch (CustomAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Endpoint to retrieve current authenticated user's details.
     *
     * @return ResponseEntity containing the user's details
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserDetails() {
        try {
            UserDTO userDTO = authenticationService.getCurrentUserDetails();
            return ResponseEntity.ok(userDTO);
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Endpoint to update the current authenticated user's profile information.
     *
     * @param updateRequestDTO the update request containing new user details
     * @return ResponseEntity containing the updated user's details
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateProfileRequestDTO updateRequestDTO) {
        try {
            UserDTO updatedUser = authenticationService.updateUserProfile(updateRequestDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/profile/themes")
    public ResponseEntity<?> getUserThemes() {
        try {
            List<ThemeDTO> userThemes = authenticationService.getCurrentUserThemes();
            return ResponseEntity.ok(userThemes);
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}
