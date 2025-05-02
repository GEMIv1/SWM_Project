package com.LMS.LMS.ServiceLayerTest;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import com.LMS.LMS.ServiceLayer.AssignmentGradesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentGradesServiceTest {

    @Mock
    private AssignmentGradesRepo assignmentGradesRepo;

    @Mock
    private AssignmentRepo assignmentRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private AssignmentGradesService assignmentGradesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSubmissionFile() {
        Long submissionId = 1L;
        AssignmentGrades submission = new AssignmentGrades();
        submission.setFileData(new byte[]{1, 2, 3});

        when(assignmentGradesRepo.findById(submissionId)).thenReturn(Optional.of(submission));

        byte[] result = assignmentGradesService.getSubmissionFile(submissionId);

        assertArrayEquals(new byte[]{1, 2, 3}, result);
    }


}