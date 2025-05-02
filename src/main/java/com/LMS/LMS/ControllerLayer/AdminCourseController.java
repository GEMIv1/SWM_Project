package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.AdminCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AdminCourse")
public class AdminCourseController {
  private final AdminCourseService adminCourseService;

    public AdminCourseController(AdminCourseService adminCourseService) {
        this.adminCourseService = adminCourseService;
    }
    @PostMapping("CreateCourse")
    public ResponseEntity<String>CreateCourse(@RequestBody CourseDTO courseDTO , User curren){
        adminCourseService.createCourse(courseDTO , curren) ;
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
    }
    @DeleteMapping("DeleteCourse/{CourseId}")
    public ResponseEntity<String>DeleteCourse(@PathVariable Long CourseId , @RequestAttribute User currentUser){
        adminCourseService.deleteCourse(CourseId , currentUser);
        return ResponseEntity.ok("Course Deleted successfully");
    }

}
