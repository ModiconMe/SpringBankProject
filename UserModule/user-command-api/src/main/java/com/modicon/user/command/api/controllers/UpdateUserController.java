package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.commands.RegisterUserCommand;
import com.modicon.user.command.api.commands.UpdateUserCommand;
import com.modicon.user.command.api.dto.RegisterUserResponse;
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
@RequestMapping("/api/v1/updateUser")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @PutMapping
    public ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command) {
        String id = UUID.randomUUID().toString();

        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new UpdateUserResponse(id, "User successfully updated"), HttpStatus.OK);
        } catch (Exception e) {
            var sageErrorMessage = "Error while processing update user request for id - " + command.getId();
            System.out.println(e);
            return new ResponseEntity<>(new UpdateUserResponse(id, sageErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
