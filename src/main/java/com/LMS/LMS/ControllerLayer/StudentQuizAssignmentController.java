package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.QuizService;
import com.LMS.LMS.ServiceLayer.StudentQuizAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentQuizAssignmentController {

    @Autowired
    private StudentQuizAssignmentService studentQuizAssignmentService;

    @PostMapping("/quiz/{quizId}/take")
    public ResponseEntity<QuizGrades> takeQuiz(
            @PathVariable Long quizId,
            @RequestParam Long studentId) {
        QuizGrades attempt = studentQuizAssignmentService.takeQuiz(quizId, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/assignment/{assignmentId}/submit")
    public ResponseEntity<AssignmentGrades> handInAssignment(
            @PathVariable Long assignmentId,
            @RequestParam Long studentId,
            @RequestParam("file") MultipartFile file) {
        AssignmentGrades submission = studentQuizAssignmentService.handInAssignment(assignmentId, studentId, file);
        return submission != null ?
                ResponseEntity.ok(submission) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/getAssignmentsGrades/{studentId}")
    public ResponseEntity<List<AssignmentGrades>> viewAssignmentsGrades(
            @PathVariable Long studentId) {
        List<AssignmentGrades> grades = studentQuizAssignmentService.viewAssignmentsGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    // instructor only?
//    @GetMapping("/getAssignmentGrade/{assignmentId}")
//    public ResponseEntity<List<AssignmentGrades>> viewAssignmentGrade(
//            @PathVariable Long assignmentId) {
//        List<AssignmentGrades> grades = studentQuizAssignmentService.viewAssignmentGrade(assignmentId);
//        return grades != null ?
//                ResponseEntity.ok(grades) :
//                ResponseEntity.notFound().build();
//    }

    @GetMapping("/getQuizzesGrades/{studentId}")
    public ResponseEntity<List<QuizGrades>> viewQuizzesGrades(
            @PathVariable Long studentId) {
        List<QuizGrades> grades = studentQuizAssignmentService.viewQuizzesGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    // instructor only?
//    @GetMapping("/getQuizGrades/{quizId}")
//    public ResponseEntity<List<QuizGrades>> viewQuizGrade(
//            @PathVariable Long quizId) {
//       List<QuizGrades> grades = studentQuizAssignmentService.viewQuizGrade(quizId);
//        return grades != null ?
//                ResponseEntity.ok(grades) :
//                ResponseEntity.notFound().build();
//    }


    @Autowired
    private QuizService quizService;

    @PostMapping("/submitQuiz/{id}")
    public ResponseEntity<QuizGrades> submitQuiz(
            @PathVariable Long id,
            @RequestBody QuizGrades submission) {
        QuizGrades submittedQuiz = quizService.submitQuiz(submission);
        return submittedQuiz != null ?
                ResponseEntity.ok(submittedQuiz) :
                ResponseEntity.badRequest().build();
    }
    @GetMapping("/VAG/{StudentId}/{AssignmentId}")
    public ResponseEntity<AssignmentGrades>ViewAssignmentGrade(@PathVariable Long StudentId ,@PathVariable Long AssignmentId ){
        AssignmentGrades assignmentGrades = studentQuizAssignmentService.ViewStudentAssignmentGrade(StudentId,AssignmentId);
     return ResponseEntity.ok(assignmentGrades);
    }
}