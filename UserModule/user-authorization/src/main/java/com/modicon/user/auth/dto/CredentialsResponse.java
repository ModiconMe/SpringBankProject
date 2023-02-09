package com.modicon.user.auth.dto;

import com.modicon.user.core.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialsResponse extends BaseResponse {
    private String accessToken;
    private String refreshToken;

    public CredentialsResponse(String message, String accessToken, String refreshToken) {
        super(message);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
