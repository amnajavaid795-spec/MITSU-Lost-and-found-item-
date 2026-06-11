package com.mitsu.mitsu.service;

import com.mitsu.mitsu.enums.ItemStatus;
import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.model.User;
import com.mitsu.mitsu.repository.LostItemRepository;
import com.mitsu.mitsu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository lostItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<LostItem> getAllItems() {
        return lostItemRepository.findAll();
    }

    public LostItem getItemById(Long id) {
        return lostItemRepository.findById(id).orElse(null);
    }

    public LostItem saveItem(LostItem item, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        item.setReportedBy(user);

        userService.addPoints(userId, 10);

        return lostItemRepository.save(item);
    }

    public LostItem updateItem(Long id, LostItem newItem) {
        LostItem item = lostItemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setItemName(newItem.getItemName());
            item.setDescription(newItem.getDescription());
            item.setCategory(newItem.getCategory());
            item.setLocationFound(newItem.getLocationFound());
            item.setDateFound(newItem.getDateFound());
            item.setStatus(newItem.getStatus());
            item.setImageUrl(newItem.getImageUrl());
            return lostItemRepository.save(item);
        }
        return null;
    }

    public void deleteItem(Long id) {
        lostItemRepository.deleteById(id);
    }

    public List<LostItem> getByStatus(ItemStatus status) {
        return lostItemRepository.findByStatus(status);
    }

    public List<LostItem> search(String name) {
        return lostItemRepository.findByItemNameContainingIgnoreCase(name);
    }

    public List<LostItem> getByUser(Long userId) {
        return lostItemRepository.findByReportedByUserId(userId);
    }
}
