package com.meetcode.backend_meetcode.graphql;

import lombok.Data;

@Data
public class UserInput {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
} 