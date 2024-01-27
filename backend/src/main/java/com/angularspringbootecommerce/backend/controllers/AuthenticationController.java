package com.angularspringbootecommerce.backend.controllers;

import com.angularspringbootecommerce.backend.dtos.UserDto;
import com.angularspringbootecommerce.backend.dtos.UserLoginDto;
import com.angularspringbootecommerce.backend.exceptions.AppException;
import com.angularspringbootecommerce.backend.models.User;
import com.angularspringbootecommerce.backend.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//this is a controller class that handles the authentication of the user (HTTP Requests)
@RestController
//this is the base path that the user will use to access the authentication controller
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    //an instance of the authentication service will be created and injected into this controller
    @Autowired
    private AuthenticationService authenticationService;

    //this is the method that maps HTTP Post Requests with "/api/v1/auth/register" endpoint.
    @PostMapping("/register")
    //this takes takes in a JSON request body and validates it using the UserDto class and the constraints there
    public User register(@Valid @RequestBody UserDto user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new AppException("All fields are required.", HttpStatus.BAD_REQUEST);
        } 
        return authenticationService.register(user.getEmail(), user.getPassword());
    }

    //this is the method that maps HTTP Post Requests with "/api/v1/auth/login" endpoint.
    @PostMapping("/login")
    public UserLoginDto login(@Valid @RequestBody UserDto user) {
        UserLoginDto userLoginDto = authenticationService.login(user.getEmail(), user.getPassword());
        System.out.println("here: " + userLoginDto.getUser());
        if (userLoginDto.getUser() == null) {
            throw new AppException("Invalid credentials.", HttpStatus.NOT_FOUND);
        }

        return userLoginDto;
    }
}
