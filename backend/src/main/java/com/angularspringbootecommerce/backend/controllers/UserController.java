package com.angularspringbootecommerce.backend.controllers;


import com.angularspringbootecommerce.backend.dtos.UserDto;
import com.angularspringbootecommerce.backend.models.User;
import com.angularspringbootecommerce.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId, Authentication authentication) {
        System.out.println("Samiksha here" + userId);
        return userService.getUserById(userId, authentication);
    }

    @PutMapping("/update/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody UserDto userDto, Authentication authentication) {
        System.out.println("Samiksha here" + userId);
        return userService.updateUserById(userId, userDto, authentication);
    }
}
