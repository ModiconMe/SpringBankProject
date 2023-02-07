package com.modicon.user.query.api;

import com.modicon.user.core.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class, args);
    }

}
