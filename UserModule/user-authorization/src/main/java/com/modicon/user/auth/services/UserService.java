package com.modicon.user.auth.services;

import com.modicon.user.auth.dto.CredentialsRequest;
import com.modicon.user.auth.dto.CredentialsResponse;
import com.modicon.user.auth.security.jwt.JwtGeneration;
import com.modicon.user.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.modicon.user.auth.exception.ApiException.exception;

public interface UserService {

    CredentialsResponse loginUser(CredentialsRequest request);

    @RequiredArgsConstructor
    @Service
    class Base implements UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder encoder;
        private final JwtGeneration jwtGeneration;

        @Override
        public CredentialsResponse loginUser(CredentialsRequest request) {
            String username = request.getUsername();
            com.modicon.user.core.models.User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> exception(HttpStatus.UNAUTHORIZED, "user with username [%s] is not found", username));

            if (!encoder.matches(request.getPassword(), user.getAccount().getPassword()))
                throw exception(HttpStatus.UNAUTHORIZED, "user with that combination of username and password is not found", username);

            var account = user.getAccount();

            return new CredentialsResponse(
                    "user successfully authenticated",
                    jwtGeneration.generateAccessToken(account),
                    jwtGeneration.generateRefreshToken(account));
        }
    }
}
