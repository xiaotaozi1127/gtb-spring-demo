package com.example.springbootdemo.infrastructure;

import com.example.springbootdemo.Domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
