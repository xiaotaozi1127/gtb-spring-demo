package com.example.springbootdemo.application;

import com.example.springbootdemo.Domain.Authority;
import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.infrastructure.AuthorityRepository;
import com.example.springbootdemo.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void addUser(User user, String authority) {
        userRepository.save(user);
        Authority authority1 = Authority.builder().username(user.getUsername()).authority(authority).build();
        authorityRepository.save(authority1);
    }
}
