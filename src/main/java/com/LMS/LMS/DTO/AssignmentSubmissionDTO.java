package com.LMS.LMS.DTO;

import org.springframework.web.multipart.MultipartFile;

public class AssignmentSubmissionDTO {
    private Long assignmentId;
    private Long studentId;
    private MultipartFile file;

    // Getters and setters
    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}