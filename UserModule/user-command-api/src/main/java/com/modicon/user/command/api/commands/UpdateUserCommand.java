package com.modicon.user.command.api.commands;

import com.modicon.user.command.api.abstractions.AbstractCommand;
import com.modicon.user.core.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Setter
@Getter
public class UpdateUserCommand extends AbstractCommand {
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;

    public UpdateUserCommand(String id, User user) {
        super(id);
        this.user = user;
    }
}
