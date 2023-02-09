package com.modicon.user.auth.controllers;

import com.modicon.user.auth.dto.CredentialsRequest;
import com.modicon.user.auth.dto.CredentialsResponse;
import com.modicon.user.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface AuthController {

    String BASE_URL = "/api/v1";

    interface Jwt {
        @GetMapping("/login")
        CredentialsResponse authenticate(@RequestBody CredentialsRequest request);
    }

    @RequiredArgsConstructor
    @RestController
    @RequestMapping(BASE_URL)
    class JwtAuthController implements Jwt {

        private final UserService userService;

        @Override
        public CredentialsResponse authenticate(CredentialsRequest request) {
            return userService.loginUser(request);
        }
    }
}
