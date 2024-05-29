package com.openclassrooms.mddapi.exception;

/**
 * Exception thrown when there is a validation error.
 * <p>
 * This exception is used to indicate that a validation check has failed,
 * typically due to invalid data provided by the user.
 */
public class CustomValidationException extends RuntimeException {

    /**
     * Constructs a new CustomValidationException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CustomValidationException(String message) {
        super(message);
    }
}
