package com.modicon.user.query.api.dto;

import com.modicon.user.core.dto.BaseResponse;
import com.modicon.user.core.models.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message) {
        super(message);
    }

    public UserLookupResponse(String message, List<User> users) {
        super(message);
        this.users = users;
    }

    public boolean isUsersNullOrEmpty() {
        return users == null || users.isEmpty();
    }

}

