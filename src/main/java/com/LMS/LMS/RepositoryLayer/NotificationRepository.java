package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientId(Long recipientId);
    List<Notification> findByRecipientIdAndIsReadFalse(Long recipientId); // Unread notifications
    List<Notification> findByTypeAndSenderId(String type, Long senderId); // Instructor notifications
}

