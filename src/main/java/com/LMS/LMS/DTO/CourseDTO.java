package com.LMS.LMS.DTO;

import com.LMS.LMS.ModelLayer.User;
import java.util.List;

public class CourseDTO {
    private String title;
    private String description;
    private String duration;
    private List<String> mediaFiles;
    private User instructor;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<String> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }
}
