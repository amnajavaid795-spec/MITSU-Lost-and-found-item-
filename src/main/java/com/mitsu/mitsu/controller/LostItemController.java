package com.mitsu.mitsu.controller;

import com.mitsu.mitsu.enums.ItemStatus;
import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    @GetMapping
    public List<LostItem> getAll() {
        return lostItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        LostItem item = lostItemService.getItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LostItem item,
                                    @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(lostItemService.saveItem(item, userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LostItem item) {
        LostItem updated = lostItemService.updateItem(id, item);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        lostItemService.deleteItem(id);
        return ResponseEntity.ok(Map.of("message", "Item deleted"));
    }

    @GetMapping("/status/{status}")
    public List<LostItem> getByStatus(@PathVariable ItemStatus status) {
        return lostItemService.getByStatus(status);
    }

    @GetMapping("/search")
    public List<LostItem> search(@RequestParam String q) {
        return lostItemService.search(q);
    }

    @GetMapping("/user/{userId}")
    public List<LostItem> getByUser(@PathVariable Long userId) {
        return lostItemService.getByUser(userId);
    }
}
