package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;

@Entity
public class AssignmentTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    private String taskDescription;
    private String expectedOutput;
    private Integer points;
    private String taskType; // e.g., "CODE", "WRITING", "PROBLEM_SOLVING"
    private String additionalResources; // Optional links or references

    // Default constructor
    public AssignmentTask() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) { this.assignment = assignment; }

    public String getTaskDescription() { return taskDescription; }
    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    public String getExpectedOutput() { return expectedOutput; }
    public void setExpectedOutput(String expectedOutput) { this.expectedOutput = expectedOutput; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public String getAdditionalResources() { return additionalResources; }
    public void setAdditionalResources(String additionalResources) { this.additionalResources = additionalResources; }
}