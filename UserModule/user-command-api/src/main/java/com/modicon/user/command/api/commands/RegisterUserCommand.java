package com.modicon.user.command.api.commands;

import com.modicon.user.command.api.abstractions.AbstractCommand;
import com.modicon.user.core.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserCommand extends AbstractCommand {
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;

    public RegisterUserCommand(String id, User user) {
        super(id);
        this.user = user;
    }
}
