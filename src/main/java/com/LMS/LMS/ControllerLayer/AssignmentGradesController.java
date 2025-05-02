package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ServiceLayer.AssignmentGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/assignment-grades")
public class AssignmentGradesController {

    @Autowired
    private AssignmentGradesService gradesService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitAssignment(
            @RequestParam("assignmentId") Long assignmentId,
            @RequestParam("studentId") Long studentId,
            @RequestParam("file") MultipartFile file) {
        try {
            AssignmentGrades submission = gradesService.submitAssignment(assignmentId, studentId, file);
            return submission != null ?
                    ResponseEntity.ok(submission) :
                    ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file upload: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download/{submissionId}")
    public ResponseEntity<byte[]> downloadSubmission(@PathVariable Long submissionId) {
        AssignmentGrades submission = gradesService.getAssignmentsGrades(submissionId)
                .stream()
                .findFirst()
                .orElse(null);

        if (submission != null && submission.getFileData() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + submission.getFileName() + "\"")
                    .contentType(MediaType.parseMediaType(submission.getFileType()))
                    .body(submission.getFileData());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{submissionId}/grade")
    public ResponseEntity<AssignmentGrades> gradeSubmission(
            @PathVariable Long submissionId,
            @RequestParam String grade,
            @RequestParam String feedback) {
        AssignmentGrades graded = gradesService.gradeSubmission(submissionId, grade, feedback);
        return graded != null ?
                ResponseEntity.ok(graded) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssignmentGrades>> getStudentGrades(@PathVariable Long studentId) {
        List<AssignmentGrades> grades = gradesService.getAssignmentsGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }
}