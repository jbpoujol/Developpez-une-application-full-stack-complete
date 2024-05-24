package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class ThemeDTO {
    private Long id;
    private String name;
    private String description;

    public ThemeDTO() {
    }

    public ThemeDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
