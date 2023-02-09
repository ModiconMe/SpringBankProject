package com.modicon.user.command.api.commands;

import com.modicon.user.command.api.abstractions.AbstractCommand;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RemoveUserCommand extends AbstractCommand {
    public RemoveUserCommand(String id) {
        super(id);
    }
}
