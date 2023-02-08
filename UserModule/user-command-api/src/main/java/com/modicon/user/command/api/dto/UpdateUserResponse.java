package com.modicon.user.command.api.dto;

public class UpdateUserResponse extends BaseResponse {

    private String id;

    public UpdateUserResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
