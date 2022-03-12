package com.example.springbootdemo.unitTest.repository;

import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.infrastructure.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void should_save_user_successfully_in_db() {
        User savedUser = User.builder().username("name").age(18).build();
        savedUser = userRepository.save(savedUser);

        Optional<User> optionalUser = userRepository.findById(savedUser.getId());

        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(savedUser.getAge(), 18);
        Assertions.assertEquals(savedUser.getUsername(), "name");
    }

}
