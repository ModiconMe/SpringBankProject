package com.modicon.user.auth.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface JwtGeneration {
    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    @RequiredArgsConstructor
    @Component
    class Base implements JwtGeneration {
        private final JwtConfig jwtConfig;

        @Override
        public String generateAccessToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return Jwts.builder().setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .setIssuer(jwtConfig.getIssuer())
                    .claim("authorities", userDetails.getAuthorities())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(jwtConfig.getAccessIssueAt())
                    .signWith(jwtConfig.getKey()).compact();
        }

        @Override
        public String generateRefreshToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return Jwts.builder().setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .setIssuer(jwtConfig.getIssuer())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(jwtConfig.getRefreshIssueAt())
                    .signWith(jwtConfig.getKey()).compact();
        }
    }
}
