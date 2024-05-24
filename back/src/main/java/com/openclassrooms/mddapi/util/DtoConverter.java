package com.openclassrooms.mddapi.util;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {

    public static ThemeDTO convertToThemeDTO(Theme theme) {
        return new ThemeDTO(theme.getId(), theme.getName(), theme.getDescription());
    }

    public static UserDTO convertToUserDTO(DBUser user) {
        List<ThemeDTO> subscribedThemes = user.getSubscribedThemes().stream()
                .map(DtoConverter::convertToThemeDTO)
                .collect(Collectors.toList());

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt() ,user.getUpdatedAt(), subscribedThemes);
    }
}
