    package com.LMS.LMS.ControllerLayer;
    import com.LMS.LMS.DTO.CourseDTO;
    import com.LMS.LMS.DTO.UpdateCourseRequest;
    import com.LMS.LMS.DTO.CreateCourseRequest;
    import com.LMS.LMS.DTO.DeleteCourseRequest;
    import com.LMS.LMS.ModelLayer.Course;
    import com.LMS.LMS.ModelLayer.User;
    import com.LMS.LMS.ServiceLayer.CourseService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Lazy;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/Courses")
    public class CourseController {
        private final CourseService courseService;

        @Autowired
        public CourseController(CourseService courseService) {
            this.courseService = courseService;
        }

        @PostMapping("CreateCourse")
        public ResponseEntity<String> createCourse(@RequestBody CreateCourseRequest request) {
            courseService.createCourse(request.getCourseDTO(), request.getCurrentUser());
            return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
        }


        @GetMapping
        public ResponseEntity<List<Course>> getAllCourses() {
            return ResponseEntity.ok(courseService.getAllCourses());
        }

        @GetMapping("/getCourse/{id}")
        public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
            return ResponseEntity.ok(courseService.getCourseById(id));
        }

        @PutMapping("/updateCourse/{id}")
        public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseRequest request) {
            courseService.updateCourse(id, request.getCourseDTO(), request.getCurrentUser());
            return ResponseEntity.ok("Course updated successfully.");
        }

        @DeleteMapping("/deleteCourse/{id}")
        public ResponseEntity<String> deleteCourse(@PathVariable Long id, @RequestBody DeleteCourseRequest request) {
            courseService.deleteCourse(id,  request.getCurrentUser());
            return ResponseEntity.ok("Course deleted successfully.");
        }

        @PostMapping("/{courseId}/enroll/{studentId}")
        public ResponseEntity<String> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
            courseService.enrollStudent(courseId, studentId);
            return ResponseEntity.ok("Student enrolled successfully.");
        }

        @GetMapping("/{courseId}/students")
        public ResponseEntity<List<User>> getEnrolledStudents(@PathVariable Long courseId) {
            return ResponseEntity.ok(courseService.getEnrolledStudents(courseId));
        }
    }
