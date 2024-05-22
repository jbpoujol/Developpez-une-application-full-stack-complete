package com.openclassrooms.mddapi.model;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String name;

    private String email;

    private String password;
}