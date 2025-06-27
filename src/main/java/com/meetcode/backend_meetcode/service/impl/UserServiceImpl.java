package com.meetcode.backend_meetcode.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetcode.backend_meetcode.Util.JwtUtil;
import com.meetcode.backend_meetcode.dto.AuthResponse;
import com.meetcode.backend_meetcode.dto.LoginRequest;
import com.meetcode.backend_meetcode.dto.UpdateBannerResponse;
import com.meetcode.backend_meetcode.entity.User;
import com.meetcode.backend_meetcode.repository.UserRepository;
import com.meetcode.backend_meetcode.service.UserService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public User register(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        

        // below could be redundant
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found with username: " + loginRequest.getUsername());
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (user.getIsBanned()) {
            throw new RuntimeException("User is banned");
        }

        if (!user.getIsActive()) {
            throw new RuntimeException("User account is not active");
        }

        user.setRefreshToken(jwtUtil.generateRefreshToken(user.getUsername()));
        userRepository.save(user);
        return new AuthResponse(
            jwtUtil.generateAccessToken(user.getUsername()),
            user.getRefreshToken()
        );
    }

    @Override
    public void updateUserScore(String username, Integer scoreToAdd) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        
        // Calculate new score, minimum 0
        int currentScore = user.getScore() != null ? user.getScore() : 0;
        int newScore = Math.max(0, currentScore + scoreToAdd);
        
        user.setScore(newScore);
        userRepository.save(user);
        
        System.out.println("Updated user " + username + " score: " + currentScore + " + " + scoreToAdd + " = " + newScore);
    }

    @Override
    public UpdateBannerResponse updateUserBanner(String username, String banner) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        
        user.setBanner(banner);
        userRepository.save(user);
        
        return new UpdateBannerResponse(username, true, banner);
    }
} 