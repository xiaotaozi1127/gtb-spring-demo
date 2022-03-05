package com.example.springbootdemo.application;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.userInterface.exception.CarNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    List<Car> cars;

    {
        Car car1 = Car.builder().id(1L).name("bmw").color("red").build();
        Car car2 = Car.builder().id(2L).name("VolV").color("pink").build();
        cars = new ArrayList<>(List.of(car1, car2));
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public Car findById(Long id) {
        Car car = cars.stream().filter(item -> Objects.equals(item.getId(), id)).findFirst().orElse(null);
        if (car == null) {
            throw new CarNotFoundException("cannot find car by id:" + id);
        }
        return car;
    }

    @Override
    public List<Car> findByColor(String color) {
        return cars.stream().filter(item -> item.getColor().equals(color)).collect(Collectors.toList());
    }
}
