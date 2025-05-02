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

    public Course createCourse(CourseDTO courseDTO, User currentUser) {
        if (currentUser.getRole() == Role.Student) {
            throw new RuntimeException("Students do not have permission to create courses");
        }

        User instructor = userRepository.findById(courseDTO.getInstructor().getID())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        // Verify instructor role
        if (instructor.getRole() != Role.Instructor) {
            throw new RuntimeException("Selected user is not an instructor");
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
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course updateCourse(Long id, CourseDTO courseDTO, User currentUser) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        // Check if user is a student
        if (currentUser.getRole() == Role.Student) {
            throw new RuntimeException("Students do not have permission to update courses");
        }

        // Check if user is the course instructor or an admin
        if (currentUser.getRole() != Role.Admin &&
                !course.getInstructor().getID().equals(currentUser.getID())) {
            throw new RuntimeException("You do not have permission to update this course");
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

    public void enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        // Verify student role
        if (student.getRole() != Role.Student) {
            throw new RuntimeException("Selected user is not a student");
        }

        // Check if student is already enrolled
        if (course.getStudents().contains(student)) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        course.getStudents().add(student);
        courseRepository.save(course);

        // Send email notification for enrollment confirmation
        emailNotificationService.sendEnrollmentConfirmation(student.getEmail(), course.getTitle());

        // Create notifications for the student
        Notification studentNotification = new Notification();
        studentNotification.setRecipientId(student.getID());
        studentNotification.setSenderId(course.getInstructor().getID());  // Instructor as the sender
        studentNotification.setMessage("You have successfully enrolled in the course: " + course.getTitle());
        studentNotification.setType("ENROLLMENT_CONFIRMATION");
        notificationRepository.save(studentNotification);

        // Create notifications for the instructor
        Notification instructorNotification = new Notification();
        instructorNotification.setRecipientId(course.getInstructor().getID());
        instructorNotification.setSenderId(student.getID());  // Student as the sender
        instructorNotification.setMessage("A student has enrolled in your course: " + course.getTitle());
        instructorNotification.setType("STUDENT_ENROLLMENT");
        notificationRepository.save(instructorNotification);
    }

    public List<User> getEnrolledStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        return course.getStudents();
    }
}
