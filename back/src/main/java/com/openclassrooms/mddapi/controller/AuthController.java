package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.RegistrationRequest;
import com.openclassrooms.mddapi.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for managing authentication processes.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Registers a new user and generates a token.
     *
     * @param registrationRequest the registration request data
     * @return a ResponseEntity containing a map with the generated token
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        Map<String, String> tokenResponse = authenticationService.registerUserAndGenerateToken(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    /**
     * Authenticates a user and generates a token.
     *
     * @param loginRequest the login request data
     * @return a ResponseEntity containing a map with the generated token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> tokenResponse = authenticationService.authenticateAndGenerateToken(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}
