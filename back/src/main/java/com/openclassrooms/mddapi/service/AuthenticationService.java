package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UpdateProfileRequestDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.exception.CustomAlreadyExistsException;
import com.openclassrooms.mddapi.exception.CustomAuthenticationException;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.RegistrationRequest;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing authentication and user profiles.
 * <p>
 * This interface provides methods for user registration, authentication, and profile management.
 */
public interface AuthenticationService {

    /**
     * Registers a new user based on the provided registration request and generates a JWT token.
     * <p>
     * This method handles the registration of a new user by taking the user's details from the
     * {@link RegistrationRequest} object. It validates the request, creates a new user in the database,
     * and then generates a JWT token for the newly created user.
     *
     * @param registrationRequest the registration request containing details such as the user's name,
     *                            email, and password.
     * @return A {@link Map} containing the JWT token for the newly registered user. The token is
     * returned as a value associated with the "token" key.
     * @throws CustomAlreadyExistsException if the email provided in the registration request is already
     *                                      in use by another user.
     * @throws RuntimeException             if an unexpected error occurs during the registration or token generation
     *                                      process.
     */
    Map<String, String> registerUserAndGenerateToken(RegistrationRequest registrationRequest);

    /**
     * Authenticates a user based on the provided login request and generates a JWT token.
     * <p>
     * This method attempts to authenticate a user using the credentials provided in the
     * {@link LoginRequest} object. Upon successful authentication, it generates a JWT token
     * for the authenticated user.
     *
     * @param loginRequest the login request containing the user's credentials, including email
     *                     and password.
     * @return A {@link Map} containing the JWT token for the authenticated user. The token is
     * returned as a value associated with the "token" key.
     * @throws CustomAuthenticationException if the authentication process fails due to invalid
     *                                       credentials.
     * @throws RuntimeException              if an unexpected error occurs during the authentication or token
     *                                       generation process.
     */
    Map<String, String> authenticateAndGenerateToken(LoginRequest loginRequest);

    /**
     * Retrieves the details of the currently authenticated user.
     *
     * @return A {@link UserDTO} containing the current user's details.
     */
    UserDTO getCurrentUserDetails();

    /**
     * Retrieves the email of the currently authenticated user.
     * <p>
     * This method is intended to be used in contexts where the user's email address is needed
     * and the user is expected to be authenticated. If no authentication information is available,
     * this method returns {@code null}.
     *
     * @return The email of the authenticated user if available, otherwise {@code null}.
     */
    String getAuthenticatedUserEmail();

    /**
     * Retrieves the username of the currently authenticated user.
     * <p>
     * This method obtains the username from the authentication principal. If the principal
     * is an instance of UserDetails, the username is directly extracted. Otherwise, the principal's
     * {@code toString()} representation is used as the username. This method returns {@code null}
     * if no authentication information is available.
     *
     * @return The username of the authenticated user if available, otherwise {@code null}.
     */
    String getAuthenticatedUsername();

    /**
     * Updates the profile of the currently authenticated user.
     *
     * @param updateRequestDTO the update request containing the new profile details.
     * @return A {@link UserDTO} containing the updated user's details.
     */
    public UserDTO updateUserProfile(UpdateProfileRequestDTO updateRequestDTO);

    /**
     * Retrieves the currently authenticated user entity.
     *
     * @return The {@link DBUser} entity representing the currently authenticated user.
     */
    DBUser getCurrentAuthenticatedUser();

    /**
     * Retrieves the themes that the currently authenticated user is subscribed to.
     *
     * @return A list of {@link ThemeDTO} objects representing the user's subscribed themes.
     */
    List<ThemeDTO> getCurrentUserThemes();
}
