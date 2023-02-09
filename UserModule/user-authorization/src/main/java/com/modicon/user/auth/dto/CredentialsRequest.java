package com.modicon.user.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialsRequest {
    @NotEmpty(message = "username is empty")
    private String username;
    @NotEmpty(message = "password is empty")
    private String password;
}
