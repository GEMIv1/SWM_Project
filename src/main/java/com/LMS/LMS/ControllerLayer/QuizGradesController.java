package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ServiceLayer.QuizGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-grades")
public class QuizGradesController {

    @Autowired
    private QuizGradesService gradesService;

    @PostMapping("/start")
    public ResponseEntity<QuizGrades> startQuiz(
            @RequestParam Long quizId,
            @RequestParam Long studentId) {
        QuizGrades attempt = gradesService.startQuizAttempt(quizId, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/{attemptId}/submit")
    public ResponseEntity<QuizGrades> submitQuiz(
            @PathVariable Long attemptId,
            @RequestBody String answers) {
        QuizGrades submission = gradesService.submitQuizAttempt(attemptId, answers);
        return submission != null ?
                ResponseEntity.ok(submission) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/{attemptId}/grade")
    public ResponseEntity<QuizGrades> gradeQuiz(
            @PathVariable Long attemptId,
            @RequestParam String grade,
            @RequestParam String feedback) {
        QuizGrades graded = gradesService.gradeQuiz(attemptId, grade, feedback);
        return graded != null ?
                ResponseEntity.ok(graded) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<QuizGrades>> getStudentQuizGrades(@PathVariable Long studentId) {
        List<QuizGrades> grades = gradesService.getStudentQuizzesGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizGrades>> getQuizGrades(@PathVariable Long quizId) {
        List<QuizGrades> grades = gradesService.getQuizGrades(quizId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }
}