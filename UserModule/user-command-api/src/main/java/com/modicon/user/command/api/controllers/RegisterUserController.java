package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.commands.RegisterUserCommand;
import com.modicon.user.command.api.dto.RegisterUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/registerUser")
public class RegisterUserController {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new RegisterUserResponse(id, "User successfully registered"), HttpStatus.CREATED);
        } catch (Exception e) {
            var sageErrorMessage = "Error while processing register user request for id - " + command.getId();
            System.out.println(e);
            return new ResponseEntity<>(new RegisterUserResponse(id, sageErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
