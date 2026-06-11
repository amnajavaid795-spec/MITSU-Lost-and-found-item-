package com.mitsu.mitsu.controller;

import com.mitsu.mitsu.dto.ClaimRequest;
import com.mitsu.mitsu.model.Claim;
import com.mitsu.mitsu.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/claims")
@CrossOrigin(origins = "*")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody ClaimRequest request) {
        try {
            return ResponseEntity.ok(claimService.submitClaim(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(claimService.approveClaim(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(claimService.rejectClaim(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public List<Claim> getByUser(@PathVariable Long userId) {
        return claimService.getClaimsByUser(userId);
    }

    @GetMapping("/item/{itemId}")
    public List<Claim> getByItem(@PathVariable Long itemId) {
        return claimService.getClaimsByItem(itemId);
    }

    @GetMapping("/pending")
    public List<Claim> getPending() {
        return claimService.getPendingClaims();
    }

    @GetMapping
    public List<Claim> getAll() {
        return claimService.getAllClaims();
    }
}
