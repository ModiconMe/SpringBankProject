package com.modicon.user.query.api.handlers;

import com.modicon.user.core.events.UserRegisteredEvent;
import com.modicon.user.core.events.UserRemovedEvent;
import com.modicon.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
