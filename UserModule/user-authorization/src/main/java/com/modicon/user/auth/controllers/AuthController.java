package com.modicon.user.auth.controllers;

import com.modicon.user.auth.dto.AccessTokenRequest;
import com.modicon.user.auth.dto.CredentialsRequest;
import com.modicon.user.auth.dto.CredentialsResponse;
import com.modicon.user.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

public interface AuthController {

    String BASE_URL = "/api/v1";

    interface Jwt {
        @PostMapping("/login")
        CredentialsResponse authenticate(@RequestBody CredentialsRequest request);

        @PostMapping("/token")
        CredentialsResponse refreshAccessToken(@RequestBody AccessTokenRequest request);
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

        @Override
        public CredentialsResponse refreshAccessToken(AccessTokenRequest request) {
            return userService.generateAccessToken(request);
        }
    }
}
