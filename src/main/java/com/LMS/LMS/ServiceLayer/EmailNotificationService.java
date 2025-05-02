package com.LMS.LMS.ServiceLayer;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }

    public void sendEnrollmentConfirmation(String studentEmail, String courseName) {
        String subject = "Enrollment Confirmation";
        String body = String.format("Dear Student, <br><br>You have successfully enrolled in the course: %s.<br>Thank you!", courseName);

        try {
            this.sendEmail(studentEmail, subject, body);
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            throw new RuntimeException("Failed to send enrollment confirmation email.", e);
        }
    }

    public void sendGradedAssignmentConfirmation(String studentEmail, String grade, String AssignmentName) {
        String subject = "Graded Assignment Confirmation";

        String body = String.format("Dear Student,\n\nYour assignment '%s' has been graded. You scored %s.\n\nBest regards,\nCourse Team", AssignmentName, grade);

        try {
            this.sendEmail(studentEmail, subject, body);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}




