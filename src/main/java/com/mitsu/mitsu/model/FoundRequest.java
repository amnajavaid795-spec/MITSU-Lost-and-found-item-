package com.mitsu.mitsu.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "found_requests")
public class FoundRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private LostItem item;

    @ManyToOne
    @JoinColumn(name = "finder_id", nullable = false)
    private User finder;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String status = "PENDING";

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getRequestId() { return requestId; }
    public LostItem getItem() { return item; }
    public User getFinder() { return finder; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setRequestId(Long requestId) { this.requestId = requestId; }
    public void setItem(LostItem item) { this.item = item; }
    public void setFinder(User finder) { this.finder = finder; }
    public void setMessage(String message) { this.message = message; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
