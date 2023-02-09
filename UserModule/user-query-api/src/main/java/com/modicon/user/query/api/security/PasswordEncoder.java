package com.modicon.user.query.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface PasswordEncoder {
    String hashPassword(String password);

    class Base implements PasswordEncoder {
        @Override
        public String hashPassword(String password) {
            var encoder = new BCryptPasswordEncoder(12);
            return encoder.encode(password);
        }
    }
}
