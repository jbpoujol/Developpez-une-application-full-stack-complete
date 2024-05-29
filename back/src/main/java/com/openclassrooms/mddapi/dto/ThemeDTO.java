package com.openclassrooms.mddapi.dto;

import lombok.Data;

/**
 * Data Transfer Object for Theme.
 */
@Data
public class ThemeDTO {
    private Long id;
    private String name;
    private String description;

    /**
     * Default constructor.
     */
    public ThemeDTO() {
    }

    /**
     * Constructs a ThemeDTO with the specified details.
     *
     * @param id the theme ID
     * @param name the theme name
     * @param description the theme description
     */
    public ThemeDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
