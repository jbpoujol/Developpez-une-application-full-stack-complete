package com.openclassrooms.mddapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a user in the database.
 * <p>
 * This class maps to a database entity for storing user information.
 * It includes details such as the user's name, email, password,
 * creation and update timestamps, and subscribed themes.
 */
@Data
@Entity
@Table(name = "users")
public class DBUser {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The email address of the user.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    /**
     * The date and time when the user account was created.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * The date and time when the user account was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The list of themes the user is subscribed to.
     */
    @ManyToMany
    @JoinTable(
            name = "user_theme",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> subscribedThemes = new ArrayList<>();

    /**
     * Sets the creation and update timestamps before persisting.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Updates the update timestamp before updating.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "DBUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
