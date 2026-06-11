package com.mitsu.mitsu.controller;

import com.mitsu.mitsu.dto.MessageRequest;
import com.mitsu.mitsu.model.Message;
import com.mitsu.mitsu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> send(@RequestBody MessageRequest request) {
        try {
            return ResponseEntity.ok(messageService.sendMessage(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam Long userId1,
                                         @RequestParam Long userId2) {
        return messageService.getConversation(userId1, userId2);
    }

    @GetMapping("/unread/{userId}")
    public List<Message> getUnread(@PathVariable Long userId) {
        return messageService.getUnreadMessages(userId);
    }

    @GetMapping("/notifications/{userId}")
    public List<Message> getNotifications(@PathVariable Long userId) {
        return messageService.getSystemMessages(userId);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<?> markRead(@PathVariable Long id) {
        messageService.markAsRead(id);
        return ResponseEntity.ok(Map.of("message", "Marked as read"));
    }
}
