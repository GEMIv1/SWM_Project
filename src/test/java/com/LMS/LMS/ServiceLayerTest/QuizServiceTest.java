package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.QuizRepo;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import com.LMS.LMS.ServiceLayer.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizServiceTest {

    @Mock
    private QuizRepo quizRepo;

    @Mock
    private QuizGradesRepo gradesRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateQuiz() {
        Quiz quiz = new Quiz();
        when(quizRepo.save(quiz)).thenReturn(quiz);

        Quiz result = quizService.createQuiz(quiz);

        assertNotNull(result);
        verify(quizRepo, times(1)).save(quiz);
    }




}