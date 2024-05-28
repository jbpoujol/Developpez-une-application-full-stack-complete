package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UpdateProfileRequestDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.excepton.CustomAlreadyExistsException;
import com.openclassrooms.mddapi.excepton.CustomAuthenticationException;
import com.openclassrooms.mddapi.excepton.CustomNotFoundException;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.RegistrationRequest;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.service.JwtService;
import com.openclassrooms.mddapi.service.impl.AuthenticationServiceImpl;
import com.openclassrooms.mddapi.util.DtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private DBUserRepository dbUserRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    private DBUser mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new DBUser();
        mockUser.setId(1L);
        mockUser.setName("Test User");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");
    }

    @Test
    public void testRegisterUserAndGenerateToken_Success() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName("Test User");
        registrationRequest.setEmail("test@example.com");
        registrationRequest.setPassword("Test@123");

        when(dbUserRepository.existsByEmail(anyString())).thenReturn(false);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateTokenForUser(any(DBUser.class))).thenReturn("jwtToken");

        Map<String, String> result = authenticationService.registerUserAndGenerateToken(registrationRequest);

        assertEquals("jwtToken", result.get("token"));
        verify(dbUserRepository, times(1)).save(any(DBUser.class));
    }

    @Test
    public void testRegisterUserAndGenerateToken_EmailAlreadyExists() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName("Test User");
        registrationRequest.setEmail("test@example.com");
        registrationRequest.setPassword("Test@123");

        when(dbUserRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(CustomAlreadyExistsException.class, () -> {
            authenticationService.registerUserAndGenerateToken(registrationRequest);
        });

        verify(dbUserRepository, never()).save(any(DBUser.class));
    }

    @Test
    public void testAuthenticateAndGenerateToken_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtService.generateToken(any(Authentication.class))).thenReturn("jwtToken");

        Map<String, String> result = authenticationService.authenticateAndGenerateToken(loginRequest);

        assertEquals("jwtToken", result.get("token"));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testGetCurrentUserDetails_Success() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(dbUserRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        UserDTO userDTO = authenticationService.getCurrentUserDetails();

        assertNotNull(userDTO);
        assertEquals("Test User", userDTO.getName());
        assertEquals("test@example.com", userDTO.getEmail());
    }

    @Test
    public void testUpdateUserProfile_Success() throws CustomNotFoundException {
        UpdateProfileRequestDTO updateRequestDTO = new UpdateProfileRequestDTO();
        updateRequestDTO.setName("Updated User");
        updateRequestDTO.setEmail("updated@example.com");

        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(dbUserRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));
        when(dbUserRepository.save(any(DBUser.class))).thenReturn(mockUser);

        UserDTO updatedUser = authenticationService.updateUserProfile(updateRequestDTO);

        assertNotNull(updatedUser);
        assertEquals("Updated User", updatedUser.getName());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    public void testGetCurrentUserThemes_Success() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(dbUserRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        mockUser.setSubscribedThemes(Collections.emptyList());

        List<ThemeDTO> themes = authenticationService.getCurrentUserThemes();

        assertNotNull(themes);
        assertTrue(themes.isEmpty());
    }
}
