package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.Question;
import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.RepositoryLayer.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuizQuestions(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}