package com.modicon.user.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class UserAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAuthorizationApplication.class, args);
    }

}
