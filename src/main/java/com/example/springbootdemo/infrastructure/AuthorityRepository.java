package com.example.springbootdemo.infrastructure;

import com.example.springbootdemo.Domain.Authority;
import com.example.springbootdemo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository <Authority, Long> {
}
