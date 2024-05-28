package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UpdateProfileRequestDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private ProfileController profileController;

    private UserDTO userDTO;
    private UpdateProfileRequestDTO updateProfileRequestDTO;
    private List<ThemeDTO> themeDTOList;

    @BeforeEach
    public void setUp() {
        // Creating a sample theme list
        themeDTOList = new ArrayList<>();
        ThemeDTO themeDTO = new ThemeDTO();
        themeDTO.setId(1L);
        themeDTO.setName("Technology");
        themeDTOList.add(themeDTO);

        // Initializing UserDTO with the necessary parameters
        userDTO = new UserDTO(1L, "Test User", "test@example.com", LocalDateTime.now(), LocalDateTime.now(), themeDTOList);

        // Initializing UpdateProfileRequestDTO
        updateProfileRequestDTO = new UpdateProfileRequestDTO();
        updateProfileRequestDTO.setEmail("newemail@example.com");
        updateProfileRequestDTO.setName("New Name");
    }

    @Test
    public void testGetCurrentUserDetails() {
        when(authenticationService.getCurrentUserDetails()).thenReturn(userDTO);

        ResponseEntity<?> response = profileController.getCurrentUserDetails();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(authenticationService, times(1)).getCurrentUserDetails();
    }

    @Test
    public void testUpdateUserProfile() {
        when(authenticationService.updateUserProfile(any(UpdateProfileRequestDTO.class))).thenReturn(userDTO);

        ResponseEntity<?> response = profileController.updateUserProfile(updateProfileRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(authenticationService, times(1)).updateUserProfile(any(UpdateProfileRequestDTO.class));
    }

    @Test
    public void testGetUserThemes() {
        when(authenticationService.getCurrentUserThemes()).thenReturn(themeDTOList);

        ResponseEntity<?> response = profileController.getUserThemes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(themeDTOList, response.getBody());
        verify(authenticationService, times(1)).getCurrentUserThemes();
    }
}
