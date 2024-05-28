package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Mock
    private JwtEncoder jwtEncoder;

    private Authentication mockAuthentication;
    private DBUser mockUser;

    @BeforeEach
    public void setUp() {
        mockAuthentication = mock(Authentication.class);
        mockUser = new DBUser();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");
    }

    @Test
    public void testGenerateToken() {
        Jwt mockJwt = createMockJwt();
        when(mockAuthentication.getName()).thenReturn("test@example.com");
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(mockJwt);

        String token = jwtService.generateToken(mockAuthentication);

        assertNotNull(token);
    }

    @Test
    public void testGenerateTokenForUser() {
        Jwt mockJwt = createMockJwt();
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(mockJwt);

        String token = jwtService.generateTokenForUser(mockUser);

        assertNotNull(token);
    }

    private Jwt createMockJwt() {
        return new Jwt("tokenValue", Instant.now(), Instant.now().plusSeconds(3600), Collections.singletonMap("alg", "HS256"), Map.of("claim", "value"));
    }
}
