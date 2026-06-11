package com.mitsu.mitsu.service;

import com.mitsu.mitsu.dto.MessageRequest;
import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.model.Message;
import com.mitsu.mitsu.model.User;
import com.mitsu.mitsu.repository.LostItemRepository;
import com.mitsu.mitsu.repository.MessageRepository;
import com.mitsu.mitsu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LostItemRepository lostItemRepository;

    public Message sendMessage(MessageRequest request) {
        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(request.getContent());
        message.setSystem(false);

        if (request.getItemId() != null) {
            LostItem item = lostItemRepository.findById(request.getItemId()).orElse(null);
            message.setItem(item);
        }

        return messageRepository.save(message);
    }

    public void sendSystemMessage(User recipient, LostItem item, String content) {
        Message message = new Message();
        message.setSender(recipient);
        message.setReceiver(recipient);
        message.setContent(content);
        message.setSystem(true);
        message.setItem(item);
        messageRepository.save(message);
    }

    public List<Message> getConversation(Long userId1, Long userId2) {
        return messageRepository.findConversation(userId1, userId2);
    }

    public List<Message> getUnreadMessages(Long userId) {
        return messageRepository.findByReceiverUserIdAndIsReadFalse(userId);
    }

    public List<Message> getSystemMessages(Long userId) {
        return messageRepository.findByReceiverUserIdAndIsSystemTrue(userId);
    }

    public void markAsRead(Long messageId) {
        Message msg = messageRepository.findById(messageId).orElse(null);
        if (msg != null) {
            msg.setRead(true);
            messageRepository.save(msg);
        }
    }
}
