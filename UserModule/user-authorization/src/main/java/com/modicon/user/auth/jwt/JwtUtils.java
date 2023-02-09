package com.modicon.user.auth.jwt;

import com.modicon.user.auth.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface JwtUtils {

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token);

    String extractUsername(String token);

    boolean isTokenExpired(String token);

    Claims extractClaims(String token);

    @RequiredArgsConstructor
    @Component
    class Base implements JwtUtils {

        private final UserService userService;
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

        @Override
        public boolean isTokenValid(String token) {
            boolean expired = isTokenExpired(token);
            Optional<UserDetails> userDetails = Optional.ofNullable(
                    userService.loadUserByUsername(extractUsername(token)));
            return (userDetails.isPresent() && !expired);
        }

        @Override
        public String extractUsername(String token) {
            Claims claims = extractClaims(token);
            return claims.getSubject();
        }

        @Override
        public boolean isTokenExpired(String token) {
            Claims claims = extractClaims(token);
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.before(Date.from(now));
        }

        @Override
        public Claims extractClaims(String token) {
            return Jwts.parserBuilder().setSigningKey(jwtConfig.getKey()).build().parseClaimsJws(token).getBody();
        }
    }
}
