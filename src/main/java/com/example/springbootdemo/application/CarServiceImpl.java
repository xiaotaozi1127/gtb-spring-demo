package com.example.springbootdemo.application;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.infrastructure.CarRepository;
import com.example.springbootdemo.infrastructure.UserRepository;
import com.example.springbootdemo.userInterface.exception.CarNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    final CarRepository carRepository;

    final UserRepository userRepository;

    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void addCar(Long userId, Car car) {
        log.info("====repository class=== {}", carRepository.getClass().getName());
        User user = userRepository.findById(userId).orElse(null);
        car.setUser(user);
        carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("cannot find car by id: " + id));
    }

    @Override
    public List<Car> findByColor(String color) {
        return null;
    }
}
