package com.modicon.user.command.api.controllers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@RequiredArgsConstructor
abstract public class CommandController {
    protected final CommandGateway commandGateway;
}
