package com.mitsu.mitsu.dto;

public class MessageRequest {
    private Long senderId;
    private Long receiverId;
    private Long itemId;
    private String content;

    public Long getSenderId() { return senderId; }
    public Long getReceiverId() { return receiverId; }
    public Long getItemId() { return itemId; }
    public String getContent() { return content; }

    public void setSenderId(Long senderId) { this.senderId = senderId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public void setContent(String content) { this.content = content; }
}
