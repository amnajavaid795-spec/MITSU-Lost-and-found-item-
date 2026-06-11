package com.mitsu.mitsu.repository;

import com.mitsu.mitsu.enums.ClaimStatus;
import com.mitsu.mitsu.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByClaimantUserId(Long userId);
    List<Claim> findByItemItemId(Long itemId);
    List<Claim> findByStatus(ClaimStatus status);
}
