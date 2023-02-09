package com.modicon.user.core.events;

import com.modicon.user.core.abstractions.AbstractEvent;
import com.modicon.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisteredEvent extends AbstractEvent {
    private User user;

    public UserRegisteredEvent(String id, User user) {
        super(id);
        this.user = user;
    }
}
