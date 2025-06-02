package com.meetcode.backend_meetcode.service;

import java.util.List;

import com.meetcode.backend_meetcode.dto.AuthResponse;
import com.meetcode.backend_meetcode.dto.LoginRequest;
import com.meetcode.backend_meetcode.entity.User;

public interface UserService {
    User register(User user);
    User getUserById(String id);
    List<User> getAllUsers();
    User updateUser(User user);
    AuthResponse login(LoginRequest loginRequest);
    User getUserByUsername(String username);
} 