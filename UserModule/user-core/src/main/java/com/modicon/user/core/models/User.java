package com.modicon.user.core.models;

import com.modicon.user.core.abstractions.AbstractEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@Document(collection = "users")
public class User extends AbstractEntity {
    @NotEmpty(message = "firstName is empty")
    private String firstName;
    @NotEmpty(message = "lastName is empty")
    private String lastName;
    @NotEmpty(message = "email is empty")
    @Email(message = "please provide a valid email address")
    private String email;
    @NotNull(message = "please provide account credentials")
    private Account account;

    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }
}
