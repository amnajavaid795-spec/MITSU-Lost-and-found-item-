package com.mitsu.mitsu.model;

import com.mitsu.mitsu.enums.ClaimStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private LostItem item;

    @ManyToOne
    @JoinColumn(name = "claimant_id", nullable = false)
    private User claimant;

    @Column(columnDefinition = "TEXT")
    private String proofDescription;

    private String proofImageUrl;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status = ClaimStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getClaimId() { return claimId; }
    public LostItem getItem() { return item; }
    public User getClaimant() { return claimant; }
    public String getProofDescription() { return proofDescription; }
    public String getProofImageUrl() { return proofImageUrl; }
    public ClaimStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setClaimId(Long claimId) { this.claimId = claimId; }
    public void setItem(LostItem item) { this.item = item; }
    public void setClaimant(User claimant) { this.claimant = claimant; }
    public void setProofDescription(String proofDescription) { this.proofDescription = proofDescription; }
    public void setProofImageUrl(String proofImageUrl) { this.proofImageUrl = proofImageUrl; }
    public void setStatus(ClaimStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
