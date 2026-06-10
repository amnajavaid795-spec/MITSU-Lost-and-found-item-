package com.mitsu.mitsu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.mitsu.mitsu.enums.ItemStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
@Entity
@Table(name = "lost_items")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String itemName;
    private String description;

    private String locationFound;
    private String dateFound;

    private String reportedBy;


    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
    public Long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public String getDateFound() {
        return dateFound;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }
}