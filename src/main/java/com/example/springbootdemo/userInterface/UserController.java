package com.example.springbootdemo.userInterface;

import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.application.UserService;
import com.example.springbootdemo.userInterface.dto.request.UserRequest;
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
            user.setPassword(userRequest.getPassword());
        }
        userService.addUser(user);
    }
}
