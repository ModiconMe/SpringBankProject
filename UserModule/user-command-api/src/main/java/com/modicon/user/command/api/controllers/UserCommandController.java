package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.commands.RegisterUserCommand;
import com.modicon.user.command.api.commands.RemoveUserCommand;
import com.modicon.user.command.api.commands.UpdateUserCommand;
import com.modicon.user.command.api.dto.RegisterUserResponse;
import com.modicon.user.command.api.dto.RemoveUserResponse;
import com.modicon.user.command.api.dto.UpdateUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface UserCommandController {

    interface Register {
        @PostMapping("/api/v1/registerUser")
        ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command);
    }

    interface Update {
        @PutMapping("/api/v1/updateUser")
        ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command);
    }

    interface Remove {
        @DeleteMapping("/api/v1/removeUser")
        ResponseEntity<RemoveUserResponse> removeUser(@Valid @RequestBody RemoveUserCommand command);
    }

    @RequiredArgsConstructor
    @RestController
    class RegisterUserController implements Register {
        private final CommandGateway commandGateway;

        @Override
        public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
            String id = commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RegisterUserResponse(id, "User successfully registered"), HttpStatus.CREATED);
        }
    }

    @RequiredArgsConstructor
    @RestController
    class UpdateUserController implements Update {
        private final CommandGateway commandGateway;

        @Override
        public ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command) {
            command.setId(UUID.randomUUID().toString());
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new UpdateUserResponse(command.getId(), "User successfully updated"), HttpStatus.OK);
        }
    }

    @RequiredArgsConstructor
    @RestController
    class RemoveUserController implements Remove {
        private final CommandGateway commandGateway;

        @Override
        public ResponseEntity<RemoveUserResponse> removeUser(@Valid @RequestBody RemoveUserCommand command) {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RemoveUserResponse(command.getId(), "User successfully deleted"), HttpStatus.OK);
        }
    }

}
