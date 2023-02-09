package com.modicon.user.query.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
