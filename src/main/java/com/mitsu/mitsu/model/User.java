package com.mitsu.mitsu.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String campusId;

    private int points = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCampusId() { return campusId; }
    public int getPoints() { return points; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setCampusId(String campusId) { this.campusId = campusId; }
    public void setPoints(int points) { this.points = points; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
