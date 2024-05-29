package com.openclassrooms.mddapi.model;

import lombok.Data;

/**
 * Data Transfer Object for registration requests.
 * <p>
 * This class encapsulates the data required for a user registration request,
 * including the user's name, email, and password.
 */
@Data
public class RegistrationRequest {

    /**
     * The name of the user registering.
     */
    private String name;

    /**
     * The email address of the user registering.
     */
    private String email;

    /**
     * The password of the user registering.
     */
    private String password;
}
