package com.mitsu.mitsu.dto;

public class AuthResponse {
    private String message;
    private Long userId;
    private String name;
    private String email;
    private String campusId;
    private int points;

    public AuthResponse(String message, Long userId, String name, String email, String campusId, int points) {
        this.message = message;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.campusId = campusId;
        this.points = points;
    }

    public String getMessage() { return message; }
    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCampusId() { return campusId; }
    public int getPoints() { return points; }
}
