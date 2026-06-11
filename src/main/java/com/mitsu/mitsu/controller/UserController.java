package com.mitsu.mitsu.controller;

import com.mitsu.mitsu.model.User;
import com.mitsu.mitsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/leaderboard")
    public List<User> getLeaderboard() {
        return userService.getAllUsers().stream()
                .sorted((a, b) -> b.getPoints() - a.getPoints())
                .toList();
    }
}
