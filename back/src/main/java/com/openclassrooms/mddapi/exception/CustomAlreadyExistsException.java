package com.openclassrooms.mddapi.exception;

/**
 * Exception thrown when an entity already exists.
 * <p>
 * This exception is used to indicate that an attempt to create or add an entity has failed
 * because the entity already exists in the system.
 */
public class CustomAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new CustomAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CustomAlreadyExistsException(String message) {
        super(message);
    }

}
