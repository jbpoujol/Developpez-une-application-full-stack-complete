package com.openclassrooms.mddapi.model;

import lombok.Data;

/**
 * Data Transfer Object for login requests.
 * <p>
 * This class encapsulates the data required for a user login request,
 * including the user's email and password.
 */
@Data
public class LoginRequest {

    /**
     * The email address of the user attempting to log in.
     */
    private String email;

    /**
     * The password of the user attempting to log in.
     */
    private String password;

}
