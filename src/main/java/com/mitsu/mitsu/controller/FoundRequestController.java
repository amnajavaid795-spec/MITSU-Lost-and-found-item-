package com.mitsu.mitsu.controller;

import com.mitsu.mitsu.model.FoundRequest;
import com.mitsu.mitsu.service.FoundRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/found-requests")
@CrossOrigin(origins = "*")
public class FoundRequestController {

    @Autowired
    private FoundRequestService foundRequestService;

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody Map<String, Object> body) {
        try {
            Long itemId   = Long.valueOf(body.get("itemId").toString());
            Long finderId = Long.valueOf(body.get("finderId").toString());
            String msg    = body.getOrDefault("message", "").toString();
            return ResponseEntity.ok(foundRequestService.submitFoundRequest(itemId, finderId, msg));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<?> accept(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(foundRequestService.acceptFoundRequest(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(foundRequestService.rejectFoundRequest(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/item/{itemId}")
    public List<FoundRequest> getByItem(@PathVariable Long itemId) {
        return foundRequestService.getByItem(itemId);
    }

    @GetMapping("/finder/{userId}")
    public List<FoundRequest> getByFinder(@PathVariable Long userId) {
        return foundRequestService.getByFinder(userId);
    }
}
