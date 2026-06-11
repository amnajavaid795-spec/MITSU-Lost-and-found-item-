package com.mitsu.mitsu.service;

import com.mitsu.mitsu.dto.ClaimRequest;
import com.mitsu.mitsu.enums.ClaimStatus;
import com.mitsu.mitsu.enums.ItemStatus;
import com.mitsu.mitsu.model.Claim;
import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.model.User;
import com.mitsu.mitsu.repository.ClaimRepository;
import com.mitsu.mitsu.repository.LostItemRepository;
import com.mitsu.mitsu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired private ClaimRepository claimRepository;
    @Autowired private LostItemRepository lostItemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private MessageService messageService;

    public Claim submitClaim(ClaimRequest request) {
        LostItem item = lostItemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        User claimant = userRepository.findById(request.getClaimantId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Claim claim = new Claim();
        claim.setItem(item);
        claim.setClaimant(claimant);
        claim.setProofDescription(request.getProofDescription());
        claim.setProofImageUrl(request.getProofImageUrl());
        claim.setStatus(ClaimStatus.PENDING);

        Claim saved = claimRepository.save(claim);

        User reporter = item.getReportedBy();
        if (reporter != null && !reporter.getUserId().equals(claimant.getUserId())) {
            messageService.sendSystemMessage(reporter, item,
                    "🔔 Mitsu: " + claimant.getName() + " has submitted a claim for your item \"" +
                            item.getItemName() + "\". Go to your posted items to review and accept or reject.");
        }

        return saved;
    }

    public Claim approveClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(ClaimStatus.APPROVED);
        claimRepository.save(claim);

        LostItem item = claim.getItem();
        item.setStatus(ItemStatus.CLAIMED);
        lostItemRepository.save(item);

        userService.addPoints(claim.getClaimant().getUserId(), 20);
        if (item.getReportedBy() != null) {
            userService.addPoints(item.getReportedBy().getUserId(), 10);
        }

        messageService.sendSystemMessage(claim.getClaimant(), item,
                "✅ Mitsu: Great news! Your claim for \"" + item.getItemName() +
                        "\" has been accepted. Please coordinate with the finder to collect your item. +20 points awarded!");

        return claim;
    }

    public Claim rejectClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(ClaimStatus.REJECTED);
        claimRepository.save(claim);

        messageService.sendSystemMessage(claim.getClaimant(), claim.getItem(),
                "❌ Mitsu: Your claim for \"" + claim.getItem().getItemName() +
                        "\" was rejected by the founder. The item details did not match your proof. You may try again with more details.");

        return claim;
    }

    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByClaimantUserId(userId);
    }

    public List<Claim> getClaimsByItem(Long itemId) {
        return claimRepository.findByItemItemId(itemId);
    }

    public List<Claim> getPendingClaims() {
        return claimRepository.findByStatus(ClaimStatus.PENDING);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
