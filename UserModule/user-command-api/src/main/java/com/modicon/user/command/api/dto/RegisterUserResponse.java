package com.modicon.user.command.api.dto;


public class RegisterUserResponse extends BaseResponse {

    private String id;

    public RegisterUserResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
