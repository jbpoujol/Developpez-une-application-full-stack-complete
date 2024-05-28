package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.RegistrationRequest;
import com.openclassrooms.mddapi.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    private RegistrationRequest registrationRequest;
    private LoginRequest loginRequest;
    private Map<String, String> tokenResponse;

    @BeforeEach
    public void setUp() {
        registrationRequest = new RegistrationRequest();
        registrationRequest.setName("Test User");
        registrationRequest.setEmail("test@example.com");
        registrationRequest.setPassword("Password1!");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("Password1!");

        tokenResponse = new HashMap<>();
        tokenResponse.put("token", "test-token");
    }

    @Test
    public void testRegisterUser() {
        when(authenticationService.registerUserAndGenerateToken(any(RegistrationRequest.class))).thenReturn(tokenResponse);

        ResponseEntity<?> response = authController.registerUser(registrationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tokenResponse, response.getBody());
        verify(authenticationService, times(1)).registerUserAndGenerateToken(any(RegistrationRequest.class));
    }

    @Test
    public void testLogin() {
        when(authenticationService.authenticateAndGenerateToken(any(LoginRequest.class))).thenReturn(tokenResponse);

        ResponseEntity<?> response = authController.login(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tokenResponse, response.getBody());
        verify(authenticationService, times(1)).authenticateAndGenerateToken(any(LoginRequest.class));
    }
}
