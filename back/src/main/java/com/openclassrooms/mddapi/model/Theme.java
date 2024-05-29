package com.openclassrooms.mddapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a theme.
 * <p>
 * This class maps to a database entity for storing theme information.
 * It includes details such as the theme's name, description, and the list of subscribers.
 */
@Data
@Entity
@Table(name = "themes")
public class Theme {
    /**
     * The unique identifier of the theme.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the theme.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the theme.
     */
    @Column(name = "description")
    private String description;

    /**
     * The list of users subscribed to the theme.
     */
    @ManyToMany(mappedBy = "subscribedThemes")
    private List<DBUser> subscribers = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Theme() {
    }

    /**
     * Constructs a new Theme with the specified name and description.
     *
     * @param name        The name of the theme.
     * @param description The description of the theme.
     */
    public Theme(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns a string representation of the theme.
     *
     * @return a string representation of the theme
     */
    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
