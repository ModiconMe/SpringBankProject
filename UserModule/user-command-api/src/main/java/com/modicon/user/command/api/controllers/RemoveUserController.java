package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.commands.RemoveUserCommand;
import com.modicon.user.command.api.commands.UpdateUserCommand;
import com.modicon.user.command.api.dto.RemoveUserResponse;
import com.modicon.user.command.api.dto.UpdateUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/removeUser")
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @DeleteMapping
    public ResponseEntity<RemoveUserResponse> removeUser(@Valid @RequestBody RemoveUserCommand command) {
        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RemoveUserResponse(command.getId(), "User successfully updated"), HttpStatus.OK);
        } catch (Exception e) {
            var sageErrorMessage = "Error while processing update user request for id - " + command.getId();
            System.out.println(e);
            return new ResponseEntity<>(new RemoveUserResponse(command.getId(), sageErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
