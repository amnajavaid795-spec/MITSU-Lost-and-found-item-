package com.mitsu.mitsu.repository;

import com.mitsu.mitsu.enums.ItemStatus;
import com.mitsu.mitsu.model.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    List<LostItem> findByStatus(ItemStatus status);
    List<LostItem> findByCategory(String category);
    List<LostItem> findByItemNameContainingIgnoreCase(String name);
    List<LostItem> findByReportedByUserId(Long userId);
}
