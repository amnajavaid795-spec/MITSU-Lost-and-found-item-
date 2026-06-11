package com.mitsu.mitsu.repository;

import com.mitsu.mitsu.model.FoundRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoundRequestRepository extends JpaRepository<FoundRequest, Long> {
    List<FoundRequest> findByItemItemId(Long itemId);
    List<FoundRequest> findByFinderUserId(Long userId);
    List<FoundRequest> findByStatus(String status);
}
