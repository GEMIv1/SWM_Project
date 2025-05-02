package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.DTO.NotificationDTO;
import com.LMS.LMS.ModelLayer.Notification;
import com.LMS.LMS.RepositoryLayer.NotificationRepository;
import com.LMS.LMS.ServiceLayer.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNotification() {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.recipientId = 1L;
        notificationDTO.senderId = 2L;
        notificationDTO.message = "Test Notification";
        notificationDTO.type = "COURSE_UPDATED";
        notificationDTO.isRead = false;

        Notification notification = new Notification();
        notification.setRecipientId(1L);
        notification.setSenderId(2L);
        notification.setMessage("Test Notification");
        notification.setType("COURSE_UPDATED");
        notification.setRead(false);

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        Notification createdNotification = notificationService.createNotification(notificationDTO);

        assertNotNull(createdNotification);
        assertEquals("Test Notification", createdNotification.getMessage());
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void testGetNotificationsForRecipient() {
        Notification notification1 = new Notification();
        notification1.setRecipientId(1L);
        notification1.setMessage("Notification 1");

        Notification notification2 = new Notification();
        notification2.setRecipientId(1L);
        notification2.setMessage("Notification 2");

        when(notificationRepository.findByRecipientId(1L)).thenReturn(Arrays.asList(notification1, notification2));
        when(notificationRepository.findByRecipientIdAndIsReadFalse(1L)).thenReturn(List.of(notification1));

        List<Notification> notifications = notificationService.getNotificationsForRecipient(1L, false);
        List<Notification> unreadNotifications = notificationService.getNotificationsForRecipient(1L, true);

        assertEquals(2, notifications.size());
        assertEquals(1, unreadNotifications.size());
        verify(notificationRepository, times(1)).findByRecipientId(1L);
        verify(notificationRepository, times(1)).findByRecipientIdAndIsReadFalse(1L);
    }

    @Test
    void testGetInstructorNotifications() {
        Notification notification = new Notification();
        notification.setSenderId(2L);
        notification.setType("ENROLLMENT_CONFIRMATION");

        when(notificationRepository.findByTypeAndSenderId("ENROLLMENT_CONFIRMATION", 2L))
                .thenReturn(List.of(notification));

        List<Notification> notifications = notificationService.getInstructorNotifications(2L);

        assertEquals(1, notifications.size());
        verify(notificationRepository, times(1)).findByTypeAndSenderId("ENROLLMENT_CONFIRMATION", 2L);
    }

    @Test
    void testMarkAsRead() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setRead(false);

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        notificationService.markAsRead(1L);

        assertTrue(notification.isRead());
        verify(notificationRepository, times(1)).save(notification);
    }
}
