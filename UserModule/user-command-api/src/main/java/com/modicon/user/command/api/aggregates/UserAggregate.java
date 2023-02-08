package com.modicon.user.command.api.aggregates;

import com.modicon.user.command.api.commands.RegisterUserCommand;
import com.modicon.user.command.api.commands.RemoveUserCommand;
import com.modicon.user.command.api.commands.UpdateUserCommand;
import com.modicon.user.command.api.security.PasswordEncoder;
import com.modicon.user.command.api.security.PasswordEncoderImpl;
import com.modicon.user.core.events.UserRegisteredEvent;
import com.modicon.user.core.events.UserRemovedEvent;
import com.modicon.user.core.events.UserUpdatedEvent;
import com.modicon.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand cmd) {
        var newUser = cmd.getUser();
        newUser.setId(UUID.randomUUID().toString());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var encodedPassword = passwordEncoder.hashPassword(password);

        newUser.getAccount().setPassword(encodedPassword);
        var event = UserRegisteredEvent.builder()
                .id(cmd.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand cmd) {
        var updatedUser = cmd.getUser();
        updatedUser.setId(cmd.getId());
        var password = updatedUser.getAccount().getPassword();
        var encodedPassword = passwordEncoder.hashPassword(password);

        updatedUser.getAccount().setPassword(encodedPassword);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand cmd) {
        var event = new UserRemovedEvent();
        event.setId(cmd.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
