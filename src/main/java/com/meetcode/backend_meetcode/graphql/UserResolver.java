package com.meetcode.backend_meetcode.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.AuthResponse;
import com.meetcode.backend_meetcode.dto.LoginRequest;
import com.meetcode.backend_meetcode.entity.User;
import com.meetcode.backend_meetcode.repository.UserRepository;
import com.meetcode.backend_meetcode.service.UserService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserResolver {

    private final UserService userService;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User getUserById(@Argument String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @QueryMapping
    public User getUserByUsername(@Argument String username) {
        return userRepository.findByUsername(username);
    }

    @MutationMapping
    public User register(@Argument("user") UserInput userInput) {
        User user = new User();
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setUsername(userInput.getUsername());
        user.setEmail(userInput.getEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));

        return userService.register(user);
    }

    @MutationMapping
    public AuthResponse login(@Argument("loginRequest") LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found with username: " + loginRequest.getUsername());
        }
        return userService.login(loginRequest);
    }   

    @MutationMapping
    public User updateUser(@Argument String id, @Argument("user") UserInput userInput) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setFirstName(userInput.getFirstName());
        existingUser.setLastName(userInput.getLastName());
        existingUser.setUsername(userInput.getUsername());
        existingUser.setEmail(userInput.getEmail());
        existingUser.setPassword(userInput.getPassword());

        return userService.updateUser(existingUser);
    }
} 