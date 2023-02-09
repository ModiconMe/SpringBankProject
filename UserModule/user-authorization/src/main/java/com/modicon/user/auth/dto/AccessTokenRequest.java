package com.modicon.user.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccessTokenRequest {
    @NotEmpty(message = "Token is empty")
    private String refreshToken;
}
