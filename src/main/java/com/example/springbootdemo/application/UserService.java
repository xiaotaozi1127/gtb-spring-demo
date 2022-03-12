package com.example.springbootdemo.application;

import com.example.springbootdemo.Domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    void addUser(User user, String authority);

}
