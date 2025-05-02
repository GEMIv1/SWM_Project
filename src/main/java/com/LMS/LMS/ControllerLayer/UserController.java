package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.LoginReq;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.Role;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {

    private final UserService userService ;
    @Autowired
    public UserController(@Lazy UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/Sign-up")
    public ResponseEntity<String>Sign_up(@RequestBody UserRegistration userReq){
        userReq.setRole(Role.Student);
        userService.Register(userReq);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered successfully.") ;
    }
    @PostMapping("/Login")
    public ResponseEntity<String>Login(@RequestBody LoginReq req){

        userService.Login(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("Login Successfully. ") ;
    }
    @GetMapping("/ViewProfile/{id}")
    public ResponseEntity<User>ViewProfile(@PathVariable Long id){

        return ResponseEntity.ok(userService.ViewProfile(id));

    }
    @PostMapping("UpdateProfile/{UserId}")
    public ResponseEntity<User>UpdateProfile(@PathVariable Long UserId, @RequestBody UserRegistration userRegistration){
       return ResponseEntity.ok(userService.UpdateProfile(UserId ,userRegistration));
    }
}

