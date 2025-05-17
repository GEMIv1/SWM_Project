package com.LMS.LMS.ServiceLayer;
import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final EmailNotificationService emailNotificationService;
    private final AssignmentRepo assignmentRepo;

    @Autowired
    public CourseService(@Lazy CourseRepository courseRepository, @Lazy UserRepository userRepository,@Lazy AssignmentRepo assignmentRepo,
                         @Lazy NotificationRepository notificationRepository, @Lazy EmailNotificationService emailNotificationService) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.emailNotificationService = emailNotificationService;
        this.assignmentRepo = assignmentRepo;
    }

    @Transactional
    public Course createCourse(CourseDTO courseDTO, User currentUser) {
        if (currentUser.getRole() == Role.Student) {
            throw new PermissionDeniedException("Students do not have permission to create courses");
        }

        User instructor = userRepository.findById(courseDTO.getInstructor().getID())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + courseDTO.getInstructor().getID()));

        if (instructor.getRole() != Role.Instructor) {
            throw new PermissionDeniedException("Selected user is not an instructor");
        }

        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

   @Transactional
    public Course updateCourse(Long id, CourseDTO courseDTO, User currentUser) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        if (currentUser.getRole() == Role.Student) {
            throw new PermissionDeniedException("Students do not have permission to update courses");
        }

        if (currentUser.getRole() != Role.Admin &&
                !course.getInstructor().getID().equals(currentUser.getID())) {
            throw new PermissionDeniedException("You do not have permission to update this course");
        }

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());

        return courseRepository.save(course);
    }

    public void deleteCourse(Long id, User currentUser) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        // Check if user is a student
        if (currentUser.getRole() == Role.Student) {
            throw new RuntimeException("Students do not have permission to delete courses");
        }

        // Check if user is the course instructor or an admin
        if (currentUser.getRole() != Role.Admin &&
                !course.getInstructor().getID().equals(currentUser.getID())) {
            throw new RuntimeException("You do not have permission to delete this course");
        }
        if (course.getAssignments() != null) {
            for (Assignment assignment : course.getAssignments()) {
                assignmentRepo.delete(assignment);
            }

            courseRepository.deleteById(id);
        }
    }

    @Transactional
    public void enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        if (student.getRole() != Role.Student) {
            throw new PermissionDeniedException("Selected user is not a student");
        }

        if (course.getStudents().contains(student)) {
            throw new DuplicateEnrollmentException("Student is already enrolled in course id: " + courseId);
        }

        course.getStudents().add(student);
        courseRepository.save(course);

        emailNotificationService.sendEnrollmentConfirmation(student.getEmail(), course.getTitle());

        Notification studentNotification = new Notification();
        studentNotification.setRecipientId(student.getID());
        studentNotification.setSenderId(course.getInstructor().getID());
        studentNotification.setMessage("Enrolled in: " + course.getTitle());
        studentNotification.setType("ENROLLMENT_CONFIRMATION");
        notificationRepository.save(studentNotification);

        Notification instructorNotification = new Notification();
        instructorNotification.setRecipientId(course.getInstructor().getID());
        instructorNotification.setSenderId(student.getID());
        instructorNotification.setMessage("New enrollment in: " + course.getTitle());
        instructorNotification.setType("STUDENT_ENROLLMENT");
        notificationRepository.save(instructorNotification);
    }

    public List<User> getEnrolledStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        return course.getStudents();
    }

    
}
