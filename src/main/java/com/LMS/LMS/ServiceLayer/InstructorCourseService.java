package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.Course;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorCourseService {
    private final CourseService courseService ;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository ;

    public InstructorCourseService(@Lazy CourseService courseService, @Lazy UserRepository userRepository, @Lazy CourseRepository courseRepository) {
        this.courseService = courseService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }
    public Course CreateCourse(CourseDTO courseDTO , User user){
       return courseService.createCourse(courseDTO, user) ;
    }
    public void removeStudentfromCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        User student = userRepository.findById(studentId).orElse(null);

        if (course != null && student != null) {
            List<User> students = course.getStudents();

            students.removeIf(s -> s.getID() == studentId.intValue());

            courseRepository.save(course);
        }
    }

}
