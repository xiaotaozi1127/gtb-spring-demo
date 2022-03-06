package com.example.springbootdemo.application;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.infrastructure.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.getById(id);
    }

    @Override
    public List<Car> findByColor(String color) {
        return null;
    }
}
