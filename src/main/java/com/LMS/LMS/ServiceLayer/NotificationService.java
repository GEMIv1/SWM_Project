package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.NotificationDTO;
import com.LMS.LMS.ModelLayer.Notification;
import com.LMS.LMS.RepositoryLayer.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setRecipientId(notificationDTO.getRecipientId());
        notification.setSenderId(notificationDTO.getSenderId());
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());
        notification.setRead(notificationDTO.getIsRead());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId, boolean onlyUnread) {
        return onlyUnread
                ? notificationRepository.findByRecipientIdAndIsReadFalse(recipientId)
                : notificationRepository.findByRecipientId(recipientId);
    }

    public List<Notification> getInstructorNotifications(Long instructorId) {
        return notificationRepository.findByTypeAndSenderId("ENROLLMENT_CONFIRMATION",instructorId );
    }

    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
