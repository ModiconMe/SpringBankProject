package com.modicon.user.query.api.handlers;

import com.modicon.user.core.events.UserRegisteredEvent;
import com.modicon.user.core.events.UserRemovedEvent;
import com.modicon.user.core.events.UserUpdatedEvent;
import com.modicon.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);

    @Slf4j
    @RequiredArgsConstructor
    @Service
    @ProcessingGroup("user-group")
    class Base implements UserEventHandler {

        private final UserRepository userRepository;

        @EventHandler
        @Override
        public void on(UserRegisteredEvent event) {
            userRepository.save(event.getUser());
        }

        @EventHandler
        @Override
        public void on(UserUpdatedEvent event) {
            userRepository.save(event.getUser());
        }

        @EventHandler
        @Override
        public void on(UserRemovedEvent event) {
            userRepository.deleteById(event.getId());
        }
    }
}
