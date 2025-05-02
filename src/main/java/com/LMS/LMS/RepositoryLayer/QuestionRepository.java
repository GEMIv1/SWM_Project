package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Question;
import com.LMS.LMS.ModelLayer.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
}
