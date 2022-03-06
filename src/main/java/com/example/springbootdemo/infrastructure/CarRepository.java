package com.example.springbootdemo.infrastructure;

import com.example.springbootdemo.Domain.Car;

import java.util.List;

public interface CarRepository {
    List<Car> getAllCars();

    Car getById(Long id);

    List<Car> getByColor(String color);

    void addCar(Car car);
}
