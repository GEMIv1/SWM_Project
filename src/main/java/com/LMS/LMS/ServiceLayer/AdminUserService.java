package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    private  UserService userService ;

    @Autowired
    public AdminUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    public User CreateUser(UserRegistration userRegistration){


        return userService.Register(userRegistration);

    }


}
