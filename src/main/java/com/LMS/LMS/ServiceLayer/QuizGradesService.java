package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import com.LMS.LMS.RepositoryLayer.QuizRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizGradesService {

    @Autowired
    private QuizGradesRepo quizGradesRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private UserRepository userRepo;

    public QuizGrades startQuizAttempt(Long quizId, Long studentId) {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        Optional<User> student = userRepo.findById(studentId);

        if (quiz.isPresent() && student.isPresent()) {
            // Check if maximum attempts reached
            Long attemptCount = quizGradesRepo.countByQuizAndStudent(quiz.get(), student.get());
            if (attemptCount < quiz.get().getMaxAttempts()) {
                QuizGrades attempt = new QuizGrades();
                attempt.setQuiz(quiz.get());
                attempt.setStudent(student.get());
                attempt.setStartTime(LocalDateTime.now());
                attempt.setAttemptNumber(attemptCount+ 1L);
                return quizGradesRepo.save(attempt);
            }
        }
        return null;
    }

    public QuizGrades submitQuizAttempt(Long attemptId, String answers) {
        Optional<QuizGrades> attempt = quizGradesRepo.findById(attemptId);
        if (attempt.isPresent()) {
            QuizGrades submission = attempt.get();
            submission.setEndTime(LocalDateTime.now());
            submission.setAnswers(answers);
            return quizGradesRepo.save(submission);
        }
        return null;
    }

    public QuizGrades gradeQuiz(Long attemptId, String grade, String feedback) {
        Optional<QuizGrades> attempt = quizGradesRepo.findById(attemptId);
        if (attempt.isPresent()) {
            QuizGrades graded = attempt.get();
            graded.setGrade(grade);
            graded.setFeedback(feedback);
            return quizGradesRepo.save(graded);
        }
        return null;
    }

    public List<QuizGrades> getStudentQuizzesGrades(Long studentId) {
        Optional<User> student = userRepo.findById(studentId);
        if (student.isPresent()) {
            // Assuming you add a findByStudent method to the repository
            return quizGradesRepo.findByStudent(student.get());
        }
        return null;
    }

    public  List<QuizGrades> getQuizGrades(Long quizId) {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        if (quiz.isPresent()) {
            // Assuming you add a findByQuiz method to the repository
            return quizGradesRepo.findByQuiz(quiz.get());
        }
        return null;
    }
}