package com.openclassrooms.mddapi.exception;

/**
 * Exception thrown when there is an authentication failure.
 * <p>
 * This exception is used to indicate that an authentication attempt has failed,
 * typically due to invalid credentials or unauthorized access.
 */
public class CustomAuthenticationException extends RuntimeException {

    /**
     * Constructs a new CustomAuthenticationException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CustomAuthenticationException(String message) {
        super(message);
    }
}
