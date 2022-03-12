package com.example.springbootdemo.userInterface;

import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.application.UserService;
import com.example.springbootdemo.userInterface.dto.request.UserRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('user')")
    public User getUserById(@PathVariable("id") Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody UserRequest userRequest) {
        User user = User.builder()
                .age(userRequest.getAge())
                .username(userRequest.getUsername())
                .build();
        if (userRequest.getPassword() != null) {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user.setPassword(encoder.encode(userRequest.getPassword()));
        }
        userService.addUser(user, userRequest.getAuthority());
    }
}
