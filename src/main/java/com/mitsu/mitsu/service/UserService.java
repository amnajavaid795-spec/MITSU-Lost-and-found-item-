package com.mitsu.mitsu.service;

import com.mitsu.mitsu.dto.AuthResponse;
import com.mitsu.mitsu.dto.LoginRequest;
import com.mitsu.mitsu.dto.RegisterRequest;
import com.mitsu.mitsu.model.User;
import com.mitsu.mitsu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (request.getCampusId() != null && userRepository.existsByCampusId(request.getCampusId())) {
            throw new RuntimeException("Campus ID already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCampusId(request.getCampusId());
        user.setPoints(0);

        User saved = userRepository.save(user);
        return new AuthResponse("Registered successfully", saved.getUserId(),
                saved.getName(), saved.getEmail(), saved.getCampusId(), saved.getPoints());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new AuthResponse("Login successful", user.getUserId(),
                user.getName(), user.getEmail(), user.getCampusId(), user.getPoints());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addPoints(Long userId, int pts) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPoints(user.getPoints() + pts);
        return userRepository.save(user);
    }
}
