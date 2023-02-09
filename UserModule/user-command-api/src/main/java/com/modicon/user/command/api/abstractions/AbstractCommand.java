package com.modicon.user.command.api.abstractions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
abstract public class AbstractCommand {
    @TargetAggregateIdentifier
    protected String id;
}
