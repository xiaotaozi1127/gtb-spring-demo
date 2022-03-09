package com.example.springbootdemo.infrastructure;

import com.example.springbootdemo.Domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByColor(String color);
}
