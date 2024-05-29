package com.openclassrooms.mddapi.exception;

/**
 * Exception thrown when an entity is not found.
 * <p>
 * This exception is used to indicate that a requested entity could not be found in the system.
 */
public class CustomNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CustomNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new CustomNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause   the cause of the exception
     */
    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
