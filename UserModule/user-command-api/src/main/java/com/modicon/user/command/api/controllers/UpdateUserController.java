package com.modicon.user.command.api.controllers;

import com.modicon.user.command.api.commands.UpdateUserCommand;
import com.modicon.user.command.api.dto.UpdateUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/updateUser")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @PutMapping
    public ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command) {
        commandGateway.sendAndWait(command);
        return new ResponseEntity<>(new UpdateUserResponse(command.getId(), "User successfully deleted"), HttpStatus.OK);
    }
}
