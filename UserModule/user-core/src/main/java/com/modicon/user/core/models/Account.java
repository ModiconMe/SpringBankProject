package com.modicon.user.core.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Builder
public class Account {
    @Size(min = 2, message = "username must have a minimum of 2 characters")
    private String username;
    @Size(min = 7, message = "username must have a minimum of 7 characters")
    private String password;
    @NotNull(message = "specify at least 1 role")
    private List<Role> roles;
}
