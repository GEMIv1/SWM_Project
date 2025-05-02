package com.LMS.LMS.DTO;

import java.time.LocalDateTime;

public class AssignmentDetailsDTO {
    private Long assignmentId;
    private String assignmentTitle;
    private LocalDateTime dueDate;
    private Double maxScore;
    private String courseTitle;
    private String courseInstructor;

    public AssignmentDetailsDTO(Long assignmentId, String assignmentTitle,

                                LocalDateTime dueDate, Double maxScore,String courseTitle, String courseInstructor) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
    }

    // Getters
    public Long getAssignmentId() { return assignmentId; }
    public String getAssignmentTitle() { return assignmentTitle; }
    public String getCourseTitle() { return courseTitle; }
    public String getCourseInstructor() { return courseInstructor; }
    public LocalDateTime getDueDate() { return dueDate; }
    public Double getMaxScore() { return maxScore; }
}