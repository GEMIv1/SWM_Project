package com.LMS.LMS.DTO;

import com.LMS.LMS.ModelLayer.Role;

public class UserRegistration {
    private String username;
    private String email;
    private String password;
    private Role role;
    public UserRegistration(String username , String email , String Password  , Role role){
        this.username = username ;
        this.email = email;
        this.password = Password ;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
