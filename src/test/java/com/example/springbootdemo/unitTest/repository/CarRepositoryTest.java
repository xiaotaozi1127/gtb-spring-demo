package com.example.springbootdemo.unitTest.repository;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.infrastructure.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Test
    public void should_get_car_by_color() {
        Car car1 = Car.builder().name("car1").color("red").build();
        car1 = carRepository.save(car1);
        Car car2 = Car.builder().name("car1").color("green").build();
        car2 = carRepository.save(car2);

        List<Car> cars = carRepository.findByColor("red");
        Assertions.assertEquals(1, cars.size());
        Assertions.assertEquals(car1.getId(), cars.get(0).getId());
    }

}
