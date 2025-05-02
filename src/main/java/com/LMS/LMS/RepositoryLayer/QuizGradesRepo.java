package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QuizGradesRepo extends JpaRepository<QuizGrades, Long> {
    @Query("SELECT qg FROM QuizGrades qg WHERE qg.student = :student")
    List<QuizGrades> findByStudent(User student);

    @Query("SELECT qg FROM QuizGrades qg WHERE qg.quiz = :quiz")
    List<QuizGrades> findByQuiz(Quiz quiz);

    @Query("SELECT COUNT(qg) FROM QuizGrades qg WHERE qg.quiz = :quiz AND qg.student = :student")
    Long countByQuizAndStudent(Quiz quiz, User student);
}