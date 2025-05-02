package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.ServiceLayer.AssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentServiceTest {

    @Mock
    private AssignmentRepo assignmentRepo;

    @Mock
    private AssignmentGradesRepo gradesRepo;

    @InjectMocks
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAssignment() {
        Assignment assignment = new Assignment();
        when(assignmentRepo.save(assignment)).thenReturn(assignment);

        Assignment result = assignmentService.createAssignment(assignment);

        assertNotNull(result);
        verify(assignmentRepo, times(1)).save(assignment);
    }

    @Test
    void testGetAssignment() {
        Long id = 1L;
        Assignment assignment = new Assignment();
        when(assignmentRepo.findById(id)).thenReturn(Optional.of(assignment));

        Optional<Assignment> result = assignmentService.getAssignment(id);

        assertTrue(result.isPresent());
        assertEquals(assignment, result.get());
    }

}