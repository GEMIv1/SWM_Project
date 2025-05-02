package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.NotificationDTO;
import com.LMS.LMS.ModelLayer.Notification;
import com.LMS.LMS.ServiceLayer.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/CreateNotification")
    public ResponseEntity<Notification> CreateNotification(@RequestBody NotificationDTO notificationDTO) {
        Notification notification = notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/{recipientId}")
    public List<Notification> getNotifications(
            @PathVariable Long recipientId,
            @RequestParam(defaultValue = "false") boolean onlyUnread) {
        return notificationService.getNotificationsForRecipient(recipientId, onlyUnread);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Notification> getInstructorNotifications(@PathVariable Long instructorId) {
        return notificationService.getInstructorNotifications(instructorId);
    }

    @PostMapping("/{id}/markAsRead")
    public void markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }
}
