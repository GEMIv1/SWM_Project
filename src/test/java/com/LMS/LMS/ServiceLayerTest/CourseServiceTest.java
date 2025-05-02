package com.LMS.LMS.ServiceLayerTest;
import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.NotificationRepository;
import com.LMS.LMS.ServiceLayer.CourseService;
import com.LMS.LMS.ServiceLayer.EmailNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private EmailNotificationService emailNotificationService;

    @Mock
    private AssignmentRepo assignmentRepo;

    @InjectMocks
    private CourseService courseService;

    private User instructor;
    private User student;
    private CourseDTO courseDTO;
    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        instructor = new User();
        instructor.setID(1L);
        instructor.setRole(Role.Instructor);

        student = new User();
        student.setID(2L);
        student.setRole(Role.Student);

        courseDTO = new CourseDTO();
        courseDTO.setTitle("Java Programming");
        courseDTO.setDescription("Learn Java from basics to advanced");
        courseDTO.setDuration("12");
        courseDTO.setInstructor(instructor);

        course = new Course();
        course.setId(1L);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from basics to advanced");
        course.setDuration("12");
        course.setInstructor(instructor);
    }

    @Test
    void testCreateCourse_Admin() {
        User admin = new User();
        admin.setRole(Role.Admin);

        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(courseDTO, admin);

        assertNotNull(createdCourse);
        assertEquals("Java Programming", createdCourse.getTitle());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testCreateCourse_Student_Fails() {
        User studentUser = new User();
        studentUser.setRole(Role.Student);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            courseService.createCourse(courseDTO, studentUser);
        });
        assertEquals("Students do not have permission to create courses", exception.getMessage());
    }

    @Test
    void testEnrollStudent_Success() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userRepository.findById(2L)).thenReturn(Optional.of(student));

        courseService.enrollStudent(1L, 2L);

        verify(courseRepository, times(1)).save(course);
        verify(emailNotificationService, times(1)).sendEnrollmentConfirmation(student.getEmail(), course.getTitle());
        verify(notificationRepository, times(2)).save(any(Notification.class));
    }

    @Test
    void testEnrollStudent_AlreadyEnrolled_Fails() {
        course.getStudents().add(student);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userRepository.findById(2L)).thenReturn(Optional.of(student));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            courseService.enrollStudent(1L, 2L);
        });
        assertEquals("Student is already enrolled in this course", exception.getMessage());
    }

    @Test
    public void testDeleteCourse_Success() {
        Course course = new Course();
        course.setId(1L);

        User instructor = new User();
        instructor.setID(1L);
        course.setInstructor(instructor);

        course.setAssignments(new ArrayList<>());

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        courseService.deleteCourse(1L, instructor);

        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteCourse_Student_Fails() {
        Course course = new Course();
        course.setId(1L);

        User student = new User();
        student.setID(2L);
        student.setRole(Role.Student);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));


        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseService.deleteCourse(1L, student);
        });

        assertEquals("Students do not have permission to delete courses", exception.getMessage());
    }


    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course));

        var courses = courseService.getAllCourses();

        assertNotNull(courses);
        assertEquals(1, courses.size());
    }

    @Test
    void testGetCourseById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course retrievedCourse = courseService.getCourseById(1L);

        assertNotNull(retrievedCourse);
        assertEquals("Java Programming", retrievedCourse.getTitle());
    }
}
