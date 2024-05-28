package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.excepton.CustomNotFoundException;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.service.impl.DBUserServiceImpl;
import com.openclassrooms.mddapi.util.DtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DBUserServiceImplTest {

    @InjectMocks
    private DBUserServiceImpl dbUserService;

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
    public void testFind_UserFound() {
        when(dbUserRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

        Optional<DBUser> user = dbUserService.find("test@example.com");

        assertTrue(user.isPresent());
        assertEquals("test@example.com", user.get().getEmail());
    }

    @Test
    public void testFind_UserNotFound() {
        when(dbUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Optional<DBUser> user = dbUserService.find("test@example.com");

        assertFalse(user.isPresent());
    }

    @Test
    public void testFindUserById_UserFound() {
        when(dbUserRepository.findById(anyLong())).thenReturn(Optional.of(mockUser));

        Optional<DBUser> user = dbUserService.findUserById(1L);

        assertTrue(user.isPresent());
        assertEquals(1L, user.get().getId());
    }

    @Test
    public void testFindUserById_UserNotFound() {
        when(dbUserRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<DBUser> user = dbUserService.findUserById(1L);

        assertFalse(user.isPresent());
    }

    @Test
    public void testFindUserDTOById_UserFound() {
        when(dbUserRepository.findById(anyLong())).thenReturn(Optional.of(mockUser));
        UserDTO mockUserDTO = DtoConverter.convertToUserDTO(mockUser);

        UserDTO userDTO = dbUserService.findUserDTOById(1L);

        assertNotNull(userDTO);
        assertEquals(mockUserDTO.getEmail(), userDTO.getEmail());
        assertEquals(mockUserDTO.getName(), userDTO.getName());
    }

    @Test
    public void testFindUserDTOById_UserNotFound() {
        when(dbUserRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> {
            dbUserService.findUserDTOById(1L);
        });
    }
}
