package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.AssignmentTask;
import com.LMS.LMS.RepositoryLayer.AssignmentTaskRepository;
import com.LMS.LMS.ServiceLayer.AssignmentTaskService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentTaskServiceTest {

    @Mock
    private AssignmentTaskRepository taskRepository;

    @InjectMocks
    private AssignmentTaskService assignmentTaskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        AssignmentTask task = new AssignmentTask();
        when(taskRepository.save(task)).thenReturn(task);

        AssignmentTask result = assignmentTaskService.createTask(task);

        assertNotNull(result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetTaskById() {
        Long id = 1L;
        AssignmentTask task = new AssignmentTask();
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        Optional<AssignmentTask> result = assignmentTaskService.getTaskById(id);

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }

    @Test
    void testDeleteTask() {
        Long id = 1L;
        when(taskRepository.existsById(id)).thenReturn(true);

        assignmentTaskService.deleteTask(id);

        verify(taskRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateTask() {
        Long id = 1L;
        AssignmentTask existingTask = new AssignmentTask();
        AssignmentTask updatedTask = new AssignmentTask();
        updatedTask.setTaskDescription("Updated description");

        when(taskRepository.findById(id)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        AssignmentTask result = assignmentTaskService.updateTask(id, updatedTask);

        assertNotNull(result);
        assertEquals("Updated description", result.getTaskDescription());
    }
}