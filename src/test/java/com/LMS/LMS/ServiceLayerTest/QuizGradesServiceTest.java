package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import com.LMS.LMS.RepositoryLayer.QuizRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import com.LMS.LMS.ServiceLayer.QuizGradesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizGradesServiceTest {

    @Mock
    private QuizGradesRepo quizGradesRepo;

    @Mock
    private QuizRepo quizRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private QuizGradesService quizGradesService;

    private Quiz quiz;
    private User student;
    private QuizGrades quizGrades;

    @BeforeEach
    public void setUp() {
        quiz = new Quiz();
        quiz.setId(1L);
        quiz.setMaxAttempts(3L);

        student = new User();

        quizGrades = new QuizGrades();
        quizGrades.setId(1L);
        quizGrades.setQuiz(quiz);
        quizGrades.setStudent(student);
    }

    @Test
    public void testStartQuizAttempt_Success() {
        when(quizRepo.findById(1L)).thenReturn(Optional.of(quiz));
        when(userRepo.findById(1L)).thenReturn(Optional.of(student));
        when(quizGradesRepo.countByQuizAndStudent(quiz, student)).thenReturn(1L);
        when(quizGradesRepo.save(any(QuizGrades.class))).thenReturn(quizGrades);

        QuizGrades result = quizGradesService.startQuizAttempt(1L, 1L);

        assertNotNull(result);
        assertEquals(quiz, result.getQuiz());
        assertEquals(student, result.getStudent());
        verify(quizGradesRepo, times(1)).save(any(QuizGrades.class));
    }

    @Test
    public void testStartQuizAttempt_MaxAttemptsReached() {
        when(quizRepo.findById(1L)).thenReturn(Optional.of(quiz));
        when(userRepo.findById(1L)).thenReturn(Optional.of(student));
        when(quizGradesRepo.countByQuizAndStudent(quiz, student)).thenReturn(3L);

        QuizGrades result = quizGradesService.startQuizAttempt(1L, 1L);

        assertNull(result);
    }

    @Test
    public void testSubmitQuizAttempt_Success() {
        when(quizGradesRepo.findById(1L)).thenReturn(Optional.of(quizGrades));
        when(quizGradesRepo.save(any(QuizGrades.class))).thenReturn(quizGrades);

        QuizGrades result = quizGradesService.submitQuizAttempt(1L, "answers");

        assertNotNull(result);
        assertEquals("answers", result.getAnswers());
        assertNotNull(result.getEndTime());
        verify(quizGradesRepo, times(1)).save(any(QuizGrades.class));
    }

    @Test
    public void testSubmitQuizAttempt_NotFound() {
        when(quizGradesRepo.findById(1L)).thenReturn(Optional.empty());

        QuizGrades result = quizGradesService.submitQuizAttempt(1L, "answers");

        assertNull(result);
    }

    @Test
    public void testGradeQuiz_Success() {
        when(quizGradesRepo.findById(1L)).thenReturn(Optional.of(quizGrades));
        when(quizGradesRepo.save(any(QuizGrades.class))).thenReturn(quizGrades);

        QuizGrades result = quizGradesService.gradeQuiz(1L, "A", "Good job");

        assertNotNull(result);
        assertEquals("Good job", result.getFeedback());
        verify(quizGradesRepo, times(1)).save(any(QuizGrades.class));
    }

    @Test
    public void testGradeQuiz_NotFound() {
        when(quizGradesRepo.findById(1L)).thenReturn(Optional.empty());

        QuizGrades result = quizGradesService.gradeQuiz(1L, "A", "Good job");

        assertNull(result);
    }

    @Test
    public void testGetStudentQuizzesGrades_Success() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(student));
        when(quizGradesRepo.findByStudent(student)).thenReturn(Collections.singletonList(quizGrades));

        List<QuizGrades> result = quizGradesService.getStudentQuizzesGrades(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(quizGrades, result.get(0));
    }

    @Test
    public void testGetStudentQuizzesGrades_NotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        List<QuizGrades> result = quizGradesService.getStudentQuizzesGrades(1L);

        assertNull(result);
    }

    @Test
    public void testGetQuizGrades_Success() {
        when(quizRepo.findById(1L)).thenReturn(Optional.of(quiz));
        when(quizGradesRepo.findByQuiz(quiz)).thenReturn(Collections.singletonList(quizGrades));

        List<QuizGrades> result = quizGradesService.getQuizGrades(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(quizGrades, result.get(0));
    }

    @Test
    public void testGetQuizGrades_NotFound() {
        when(quizRepo.findById(1L)).thenReturn(Optional.empty());

        List<QuizGrades> result = quizGradesService.getQuizGrades(1L);

        assertNull(result);
    }
}