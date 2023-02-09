package com.modicon.user.core.events;

import com.modicon.user.core.abstractions.AbstractEvent;

public class UserRemovedEvent extends AbstractEvent {
    public UserRemovedEvent(String id) {
        super(id);
    }
}
