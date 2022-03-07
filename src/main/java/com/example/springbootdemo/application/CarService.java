package com.example.springbootdemo.application;

import com.example.springbootdemo.Domain.Car;
import java.util.List;

public interface CarService {
    List<Car> findAll();

    void addCar(Long userId, Car car);

    Car findById(Long id);

    List<Car> findByColor(String color);

}
