package com.LMS.LMS.ControllerLayer;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ServiceLayer.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AdminUser")
public class AdminUserController {


    @Autowired
    private  AdminUserService adminUserService ;

   @PostMapping("/CreateUser")
    public ResponseEntity<String> CreateUser(@RequestBody UserRegistration userRegistration){

          adminUserService.CreateUser(userRegistration);
          return ResponseEntity.status(HttpStatus.CREATED).body("Admin created "+userRegistration.getRole() + " " + userRegistration.getUsername() + " Successfully") ;

    }
}
