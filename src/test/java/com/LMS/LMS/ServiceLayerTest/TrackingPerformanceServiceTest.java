package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AttendanceRepo;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import com.LMS.LMS.ServiceLayer.TrackingPerformanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackingPerformanceServiceTest {

    @InjectMocks
    private TrackingPerformanceService trackingPerformanceService;

    @Mock
    private AttendanceRepo attendanceRepo;

    @Mock
    private QuizGradesRepo quizGradesRepo;

    @Mock
    private AssignmentGradesRepo assignmentGradesRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimplifiedPerformance() {
        User student = new User();
        Course course = new Course();
        Lesson lesson = new Lesson();
        lesson.setCourse(course);

        Attendance attendance1 = new Attendance();
        attendance1.setStudent(student);
        attendance1.setLesson(lesson);
        attendance1.setAttend(true);

        Attendance attendance2 = new Attendance();
        attendance2.setStudent(student);
        attendance2.setLesson(lesson);
        attendance2.setAttend(false);

        List<Attendance> attendanceRecords = Arrays.asList(attendance1, attendance2);
        when(attendanceRepo.findAll()).thenReturn(attendanceRecords);

        Quiz quiz = new Quiz();
        quiz.setCourse(course);
        quiz.setTitle("Quiz 1");

        QuizGrades quizGrades = new QuizGrades();
        quizGrades.setStudent(student);
        quizGrades.setQuiz(quiz);
        quizGrades.setGrade("B+");

        List<QuizGrades> quizGradesRecords = Collections.singletonList(quizGrades);
        when(quizGradesRepo.findAll()).thenReturn(quizGradesRecords);

        Assignment assignment = new Assignment();
        assignment.setCourse(course);
        assignment.setTitle("Assignment 1");

        AssignmentGrades assignmentGrades = new AssignmentGrades();
        assignmentGrades.setStudent(student);
        assignmentGrades.setAssignment(assignment);
        assignmentGrades.setGrade("B+");
        assignmentGrades.setFeedback("Well done!");

        List<AssignmentGrades> assignmentGradesRecords = Collections.singletonList(assignmentGrades);
        when(assignmentGradesRepo.findAll()).thenReturn(assignmentGradesRecords);

        Map<String, Object> performanceData = trackingPerformanceService.getSimplifiedPerformance(student, course);

        assertNotNull(performanceData);
        assertEquals("1/2 sessions", performanceData.get("attendance"));

        List<Map<String, String>> quizScores = (List<Map<String, String>>) performanceData.get("quizScores");
        assertEquals(1, quizScores.size());
        assertEquals("Quiz 1", quizScores.get(0).get("quizTitle"));
        assertEquals("B+", quizScores.get(0).get("Grades"));

        @SuppressWarnings("unchecked")
        List<Map<String, String>> assignments = (List<Map<String, String>>) performanceData.get("assignments");
        assertEquals(1, assignments.size());
        assertEquals("Assignment 1", assignments.get(0).get("assignmentTitle"));
        assertEquals("B+", assignments.get(0).get("grade"));
        assertEquals("Well done!", assignments.get(0).get("feedback"));

        verify(attendanceRepo, times(1)).findAll();
        verify(quizGradesRepo, times(1)).findAll();
        verify(assignmentGradesRepo, times(1)).findAll();
    }
}