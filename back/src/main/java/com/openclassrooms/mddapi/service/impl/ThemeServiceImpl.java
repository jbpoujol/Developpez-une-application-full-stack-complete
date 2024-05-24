package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.excepton.CustomNotFoundException;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.service.AuthenticationService;
import com.openclassrooms.mddapi.service.ThemeService;
import com.openclassrooms.mddapi.util.DtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;
    private final DBUserRepository userRepository;
    private final AuthenticationService authenticationService;

    public ThemeServiceImpl(ThemeRepository themeRepository, DBUserRepository userRepository, AuthenticationService authenticationService) {
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<ThemeDTO> getAllThemes() {
        return themeRepository.findAll().stream()
                .map(DtoConverter::convertToThemeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void subscribeToTheme(Long themeId) throws CustomNotFoundException {
        DBUser currentUser = authenticationService.getCurrentAuthenticatedUser();
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new CustomNotFoundException("Theme not found"));

        currentUser.getSubscribedThemes().add(theme);
        userRepository.save(currentUser);
    }

    @Override
    public void unsubscribeFromTheme(Long themeId) throws CustomNotFoundException {
        DBUser currentUser = authenticationService.getCurrentAuthenticatedUser();
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new CustomNotFoundException("Theme not found"));

        currentUser.getSubscribedThemes().remove(theme);
        userRepository.save(currentUser);
    }

}
