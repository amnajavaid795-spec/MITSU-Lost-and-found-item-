package com.mitsu.mitsu.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private LostItem item;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private boolean isRead = false;

    private boolean isSystem = false;

    private LocalDateTime sentAt = LocalDateTime.now();

    public Long getMessageId() { return messageId; }
    public User getSender() { return sender; }
    public User getReceiver() { return receiver; }
    public LostItem getItem() { return item; }
    public String getContent() { return content; }
    public boolean isRead() { return isRead; }
    public boolean isSystem() { return isSystem; }
    public LocalDateTime getSentAt() { return sentAt; }

    public void setMessageId(Long messageId) { this.messageId = messageId; }
    public void setSender(User sender) { this.sender = sender; }
    public void setReceiver(User receiver) { this.receiver = receiver; }
    public void setItem(LostItem item) { this.item = item; }
    public void setContent(String content) { this.content = content; }
    public void setRead(boolean read) { isRead = read; }
    public void setSystem(boolean system) { isSystem = system; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
