package com.openclassrooms.mddapi.excepton;


public class CustomAlreadyExistsException extends RuntimeException {

    public CustomAlreadyExistsException(String message) {
        super(message);
    }

}
