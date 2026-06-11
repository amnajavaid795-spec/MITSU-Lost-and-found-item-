package com.mitsu.mitsu.model;

import com.mitsu.mitsu.enums.ItemStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    private String description;
    private String category;
    private String locationFound;
    private String dateFound;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.LOST;

    @ManyToOne
    @JoinColumn(name = "reported_by_user_id")
    private User reportedBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getLocationFound() { return locationFound; }
    public String getDateFound() { return dateFound; }
    public String getImageUrl() { return imageUrl; }
    public ItemStatus getStatus() { return status; }
    public User getReportedBy() { return reportedBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setItemId(Long itemId) { this.itemId = itemId; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    public void setLocationFound(String locationFound) { this.locationFound = locationFound; }
    public void setDateFound(String dateFound) { this.dateFound = dateFound; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setStatus(ItemStatus status) { this.status = status; }
    public void setReportedBy(User reportedBy) { this.reportedBy = reportedBy; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
