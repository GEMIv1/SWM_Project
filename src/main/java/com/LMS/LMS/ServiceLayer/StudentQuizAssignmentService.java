package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.NotificationRepository;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentQuizAssignmentService {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizGradesService quizGradesService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentGradesService assignmentGradesService;
    @Autowired
    private AssignmentGradesRepo assignmentGradesRepo ;
    @Autowired
    private AssignmentRepo assignmentRepo ;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private EmailNotificationService emailnotificationService ;
    @Autowired
    private  NotificationRepository notificationRepository;

    public QuizGrades takeQuiz(Long quizId, Long studentId) {
        // Start a new quiz attempt
        return quizService.startQuiz(quizId, studentId);
    }


    public AssignmentGrades handInAssignment(Long assignmentId, Long studentId, MultipartFile file) {
        try {
            // Submit an assignment with file handling
            return assignmentGradesService.submitAssignment(assignmentId, studentId, file);
        } catch (IOException e) {
            // Handle the IOException from file processing
            throw new RuntimeException("Error processing assignment submission file: " + e.getMessage(), e);
        }
    }
    public List<AssignmentGrades> viewAssignmentsGrades(Long studentId) {
        // View student's assignment grades
        return assignmentGradesService.getAssignmentsGrades(studentId);
    }

    public List<AssignmentGrades> viewAssignmentGrade(Long assignmentId) {
        // View student's assignment grades
        return assignmentGradesService.getAssignmentGrade(assignmentId);
    }
    public AssignmentGrades ViewStudentAssignmentGrade( Long studentId , Long assignmentId){
        Assignment assignment = assignmentRepo.findById(assignmentId).orElse(null);
        User Student = userRepository.findById(studentId).orElse(null);
        AssignmentGrades assignmentGrades = assignmentGradesRepo.findByStudentAndAssignment(Student,assignment);
        emailnotificationService.sendGradedAssignmentConfirmation(Student.getEmail(),assignmentGrades.getGrade() , assignment.getTitle());

        Notification studentNotification = new Notification();
        studentNotification.setRecipientId(Student.getID());
        studentNotification.setSenderId(assignment.getInstructor().getID());
        studentNotification.setMessage("Your assignment '" + assignment.getTitle() + "' has been graded. Your grade: " + assignmentGrades.getGrade());
        studentNotification.setType("ASSIGNMENT_GRADED");
        notificationRepository.save(studentNotification);
        return assignmentGrades ;
    }


    public List<QuizGrades> viewQuizzesGrades(Long studentId) {
        // View student's quiz grades
        return quizGradesService.getStudentQuizzesGrades(studentId);
    }

    public List<QuizGrades> viewQuizGrade(Long quizId) {
        // View student's quiz grades
        return quizGradesService.getQuizGrades(quizId);
    }
}