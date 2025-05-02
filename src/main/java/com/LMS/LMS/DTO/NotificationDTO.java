package com.LMS.LMS.DTO;

public class NotificationDTO {
    public Long recipientId;
    public Long senderId;
    public String message;
    public String type;
    public boolean isRead; // Add this field

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

    public boolean getIsRead() { // Add getter for isRead
        return isRead;
    }
}
