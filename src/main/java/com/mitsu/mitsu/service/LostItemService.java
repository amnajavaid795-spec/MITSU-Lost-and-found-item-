package com.mitsu.mitsu.service;

import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mitsu.mitsu.enums.ItemStatus;
import java.util.List;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository lostItemRepository;

    public List<LostItem> getAllItems() {
        return lostItemRepository.findAll();
    }

    public LostItem saveItem(LostItem item) {
        return lostItemRepository.save(item);
    }
    public LostItem getItemById(Long id) {
        return lostItemRepository.findById(id).orElse(null);
    }
    public void deleteItem(Long id) {
        lostItemRepository.deleteById(id);
    }

    public LostItem updateItem(Long id, LostItem newItem) {
        LostItem item = lostItemRepository.findById(id).orElse(null);

        if (item != null) {
            item.setItemName(newItem.getItemName());
            item.setDescription(newItem.getDescription());
            item.setLocationFound(newItem.getLocationFound());
            item.setDateFound(newItem.getDateFound());
            item.setStatus(newItem.getStatus());
            item.setReportedBy(newItem.getReportedBy());

            return lostItemRepository.save(item);
        }

        return null;
    }
    public List<LostItem> getByStatus(ItemStatus status) {
        return lostItemRepository.findByStatus(status);
    }
}