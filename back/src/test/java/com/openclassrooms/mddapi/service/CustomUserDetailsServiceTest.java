package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.service.impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private DBUserRepository dbUserRepository;

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
    public void testLoadUserByUsername_UserFound() {
        when(dbUserRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("USER")));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(dbUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("test@example.com");
        });
    }
}
