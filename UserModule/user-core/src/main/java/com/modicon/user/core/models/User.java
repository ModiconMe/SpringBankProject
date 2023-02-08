package com.modicon.user.core.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotEmpty(message = "firstName is empty")
    private String firstName;
    @NotEmpty(message = "lastName is empty")
    private String lastName;
    @NotEmpty(message = "email is empty")
    @Email(message = "please provide a valid email address")
    private String email;
    @NotNull(message = "please provide account credentials")
    private Account account;
}
