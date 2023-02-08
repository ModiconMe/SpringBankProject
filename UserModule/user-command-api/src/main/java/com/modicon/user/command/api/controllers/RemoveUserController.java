package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.commands.RemoveUserCommand;
import com.modicon.user.command.api.dto.RemoveUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/removeUser")
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @DeleteMapping
    public ResponseEntity<RemoveUserResponse> removeUser(@Valid @RequestBody RemoveUserCommand command) {
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(new RemoveUserResponse(command.getId(), "User successfully updated"), HttpStatus.OK);
    }
}
