package com.example.springbootdemo.infrastructure;

import com.example.springbootdemo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
