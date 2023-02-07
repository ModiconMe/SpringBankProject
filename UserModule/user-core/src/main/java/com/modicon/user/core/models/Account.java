package com.modicon.user.core.models;

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
    private String username;
    private String password;
    private List<Role> roles;
}
