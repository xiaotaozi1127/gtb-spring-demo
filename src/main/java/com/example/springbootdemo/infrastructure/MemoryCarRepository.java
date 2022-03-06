package com.example.springbootdemo.infrastructure;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.userInterface.exception.CarNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MemoryCarRepository implements CarRepository {

    Car car1 = Car.builder().id(1L).name("bmw").color("red").build();
    Car car2 = Car.builder().id(2L).name("VolV").color("pink").build();
    List<Car> cars = new ArrayList<>(List.of(car1, car2));

    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getById(Long id) {
        Car car = cars.stream().filter(item -> Objects.equals(item.getId(), id)).findFirst().orElse(null);
        if (car == null) {
            throw new CarNotFoundException("cannot find car by id:" + id);
        }
        return car;
    }

    @Override
    public List<Car> getByColor(String color) {
        return cars.stream().filter(item -> item.getColor().equals(color)).collect(Collectors.toList());
    }

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }
}
