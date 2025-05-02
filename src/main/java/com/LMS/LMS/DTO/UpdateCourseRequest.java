package com.LMS.LMS.DTO;

import com.LMS.LMS.ModelLayer.User;

public class UpdateCourseRequest {
    private CourseDTO courseDTO;
    private User currentUser;

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}


