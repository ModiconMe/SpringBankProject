package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.abstractions.AbstractCommandController;
import com.modicon.user.command.api.commands.RegisterUserCommand;
import com.modicon.user.command.api.commands.RemoveUserCommand;
import com.modicon.user.command.api.commands.UpdateUserCommand;
import com.modicon.user.command.api.dto.RegisterUserResponse;
import com.modicon.user.command.api.dto.RemoveUserResponse;
import com.modicon.user.command.api.dto.UpdateUserResponse;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface UserCommandController {

    String BASE_URL_V1 = "/api/v1";

    interface Register {
        @PostMapping("/registerUser")
        ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command);
    }

    interface Update {
        @PutMapping("/updateUser")
        ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command);
    }

    interface Remove {
        @DeleteMapping("/removeUser")
        ResponseEntity<RemoveUserResponse> removeUser(@Valid @RequestBody RemoveUserCommand command);
    }

    @RestController
    @RequestMapping(BASE_URL_V1)
    class RegisterUserController extends AbstractCommandController implements Register {

        public RegisterUserController(CommandGateway commandGateway) {
            super(commandGateway);
        }

        @Override
        public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
            command.setId(UUID.randomUUID().toString());
            String id = commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RegisterUserResponse(id, "User successfully registered"), HttpStatus.CREATED);
        }
    }

    @RestController
    @RequestMapping(BASE_URL_V1)
    class UpdateUserController extends AbstractCommandController implements Update {

        public UpdateUserController(CommandGateway commandGateway) {
            super(commandGateway);
        }

        @Override
        public ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command) {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new UpdateUserResponse(command.getId(), "User successfully updated"), HttpStatus.OK);
        }
    }

    @RestController
    @RequestMapping(BASE_URL_V1)
    class RemoveUserController extends AbstractCommandController implements Remove {

        public RemoveUserController(CommandGateway commandGateway) {
            super(commandGateway);
        }

        @Override
        public ResponseEntity<RemoveUserResponse> removeUser(@Valid @RequestBody RemoveUserCommand command) {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RemoveUserResponse(command.getId(), "User successfully deleted"), HttpStatus.OK);
        }
    }

}
