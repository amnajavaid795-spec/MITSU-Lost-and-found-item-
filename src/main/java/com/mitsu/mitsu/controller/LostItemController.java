package com.mitsu.mitsu.controller;

import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mitsu.mitsu.enums.ItemStatus;
import java.util.List;

@RestController
@RequestMapping("/items")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    @GetMapping
    public List<LostItem> getItems() {
        return lostItemService.getAllItems();
    }

    @PostMapping
    public LostItem addItem(@RequestBody LostItem item) {
        return lostItemService.saveItem(item);
    }
    @GetMapping("/{id}")
    public LostItem getItem(@PathVariable Long id) {
        return lostItemService.getItemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        lostItemService.deleteItem(id);
    }
    @PutMapping("/{id}")
    public LostItem updateItem(@PathVariable Long id, @RequestBody LostItem item) {
        return lostItemService.updateItem(id, item);
    }
    @GetMapping("/status/{status}")
    public List<LostItem> getByStatus(@PathVariable ItemStatus status) {
        return lostItemService.getByStatus(status);
    }
}