package com.mitsu.mitsu.dto;

public class ClaimRequest {
    private Long itemId;
    private Long claimantId;
    private String proofDescription;
    private String proofImageUrl;

    public Long getItemId() { return itemId; }
    public Long getClaimantId() { return claimantId; }
    public String getProofDescription() { return proofDescription; }
    public String getProofImageUrl() { return proofImageUrl; }

    public void setItemId(Long itemId) { this.itemId = itemId; }
    public void setClaimantId(Long claimantId) { this.claimantId = claimantId; }
    public void setProofDescription(String proofDescription) { this.proofDescription = proofDescription; }
    public void setProofImageUrl(String proofImageUrl) { this.proofImageUrl = proofImageUrl; }
}
