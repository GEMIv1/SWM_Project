package com.LMS.LMS.ModelLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Long maxScore;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore // Prevents serialization of course to avoid infinite loop
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    // Constructors
    public Assignment() {}

    public Assignment(Long id, String title, String description,
                      LocalDateTime dueDate, Long maxScore, Course course) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public Long getMaxScore() { return maxScore; }
    public void setMaxScore(Long maxScore) { this.maxScore = maxScore; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public User getInstructor() {
        return instructor;
    }
    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

}