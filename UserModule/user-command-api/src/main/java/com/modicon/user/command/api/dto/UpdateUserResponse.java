package com.modicon.user.command.api.dto;

import com.modicon.user.core.dto.BaseResponse;

public class UpdateUserResponse extends BaseResponse {

    private String id;

    public UpdateUserResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
