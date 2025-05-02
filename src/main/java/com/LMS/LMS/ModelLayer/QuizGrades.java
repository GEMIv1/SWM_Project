package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_grades")
public class QuizGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    private String grade;
    private String feedback;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "attempt_number")
    private Long attemptNumber;

    private String answers;

    // Default constructor
    public QuizGrades() {}

    // Parameterized constructor
    public QuizGrades(Long id, Quiz quiz, User student, String grade,
                      String feedback, LocalDateTime startTime,
                      LocalDateTime endTime, Long attemptNumber,
                      String answers) {
        this.id = id;
        this.quiz = quiz;
        this.student = student;
        this.grade = grade;
        this.feedback = feedback;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attemptNumber = attemptNumber;
        this.answers = answers;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getGrades() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(Long attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }


}