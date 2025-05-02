package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recipientId;
    private Long senderId;
    private String message;
    private String type;      // E.g., COURSE_UPDATED, LESSON_ADDED

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean isRead = false;

    public Long getId() {
        return id;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}

