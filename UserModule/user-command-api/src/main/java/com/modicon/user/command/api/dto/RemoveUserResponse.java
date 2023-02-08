package com.modicon.user.command.api.dto;

public class RemoveUserResponse extends BaseResponse {

    private String id;

    public RemoveUserResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
