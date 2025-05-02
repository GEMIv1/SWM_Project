package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}