package com.LMS.LMS.DTO;

import java.time.LocalDateTime;

public class QuizDetailsDTO {
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long maxScore;
    private String courseTitle;
    private String courseInstructor;

    // Constructor
    public QuizDetailsDTO(Long id, String title, LocalDateTime startTime,
                          LocalDateTime endTime, Long maxScore ,String courseTitle,
                          String courseInstructor) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxScore = maxScore;
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCourseTitle() { return courseTitle; }
    public String getCourseInstructor() { return courseInstructor; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Long getMaxScore() { return maxScore; }
}