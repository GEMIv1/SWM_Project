package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.QuizRepo;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizGradesRepo gradesRepo;

    @Autowired
    private UserRepository userRepo;

    public Quiz createQuiz(Quiz quiz)
    {
        return quizRepo.save(quiz);
    }

    public Optional<Quiz> getQuiz(Long id) {
        return quizRepo.findById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    public QuizGrades startQuiz(Long quizId, Long studentId) {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        Optional<User> student = userRepo.findById(studentId);

        if (quiz.isPresent() && student.isPresent()) {
            Quiz quizEntity = quiz.get();
            LocalDateTime now = LocalDateTime.now();

            if (now.isBefore(quizEntity.getStartTime()) || now.isAfter(quizEntity.getEndTime())) {
                return null;
            }

            Long attemptCount = gradesRepo.countByQuizAndStudent(quizEntity, student.get());
            if (attemptCount >= quizEntity.getMaxAttempts()) {
                return null;
            }

            QuizGrades attempt = new QuizGrades();
            attempt.setQuiz(quizEntity);
            attempt.setStudent(student.get());
            attempt.setStartTime(now);
            attempt.setAttemptNumber(attemptCount + 1);

            return gradesRepo.save(attempt);
        }
        return null;
    }

    public QuizGrades submitQuiz(QuizGrades submission) {
        if (submission.getQuiz() != null && submission.getStartTime() != null) {
            submission.setEndTime(LocalDateTime.now());
            return gradesRepo.save(submission);
        }
        return null;
    }
    public void DeleteQuiz(long id){
        Quiz quiz = quizRepo.findById(id).orElse(null);
        if (quiz != null)
        {
            quizRepo.delete(quiz);
        }
        throw new RuntimeException("Quiz is not Found!");
    }
}