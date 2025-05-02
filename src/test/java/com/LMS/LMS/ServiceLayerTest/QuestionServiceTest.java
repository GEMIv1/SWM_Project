package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.Question;
import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.RepositoryLayer.QuestionRepository;
import com.LMS.LMS.ServiceLayer.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateQuestion() {
        Question question = new Question();
        when(questionRepository.save(question)).thenReturn(question);

        Question result = questionService.createQuestion(question);

        assertNotNull(result);
        verify(questionRepository, times(1)).save(question);
    }

    @Test
    void testGetQuizQuestions() {
        Quiz quiz = new Quiz();
        List<Question> questions = Collections.singletonList(new Question());
        when(questionRepository.findByQuiz(quiz)).thenReturn(questions);

        List<Question> result = questionService.getQuizQuestions(quiz);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteQuestion() {
        Long id = 1L;

        questionService.deleteQuestion(id);

        verify(questionRepository, times(1)).deleteById(id);
    }
}