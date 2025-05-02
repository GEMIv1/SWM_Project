package com.LMS.LMS.ModelLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long maxAttempts = 1L;
    private Long timeLimit;
    private Long maxScore;

//    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
//    private List<Question> questions;


    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore // Prevents serialization of course to avoid infinite loop
    private Course course;

//    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
//    private List<QuizGrades> grades;

    // Constructors
    public Quiz() {}

    public Quiz(Long id, String title, String description, LocalDateTime startTime,
                LocalDateTime endTime, Long maxAttempts, Long timeLimit,
                Long maxScore, Course course) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxAttempts = maxAttempts;
        this.timeLimit = timeLimit;
        this.maxScore = maxScore;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Long getMaxAttempts() { return maxAttempts; }
    public void setMaxAttempts(Long maxAttempts) { this.maxAttempts = maxAttempts; }

    public Long getTimeLimit() { return timeLimit; }
    public void setTimeLimit(Long timeLimit) { this.timeLimit = timeLimit; }

    public Long getMaxScore() { return maxScore; }
    public void setMaxScore(Long maxScore) { this.maxScore = maxScore; }

    public Course getCourse() { return course ; }
    public void setCourse(Course course) { this.course = course; }

//    public List<QuizGrades> getGrades() { return grades; }
//    public void setGrades(List<QuizGrades> grades) { this.grades = grades; }

//    public List<Question> getQuestions() { return questions; }
//    public void setQuestions(List<Question> questions) { this.questions = questions; }
}