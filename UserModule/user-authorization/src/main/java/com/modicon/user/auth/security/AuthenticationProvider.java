package com.modicon.user.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface AuthenticationProvider {

    Authentication getAuthentication(String username);

    @Component
    @RequiredArgsConstructor
    class Base implements AuthenticationProvider {

        private final UserDetailsService userDetailsService;

        @Override
        public Authentication getAuthentication(String username) {
            return Optional.ofNullable(username)
                    .map(userDetailsService::loadUserByUsername)
                    .map(userDetails ->
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()))
                    .orElse(null);
        }
    }
}
