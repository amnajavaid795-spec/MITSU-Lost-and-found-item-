package com.mitsu.mitsu.repository;

import com.mitsu.mitsu.model.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.mitsu.mitsu.enums.ItemStatus;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    List<LostItem> findByStatus(ItemStatus status);
}