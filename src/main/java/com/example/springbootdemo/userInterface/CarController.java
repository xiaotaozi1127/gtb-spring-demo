package com.example.springbootdemo.userInterface;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.application.CarService;
import com.example.springbootdemo.userInterface.dto.request.CarRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;

//import lombok.extern.slf4j.Slf4j;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class CarController {

    private final CarService carService;

//    Logger log = LoggerFactory.getLogger(CarController.class);

//    Logger log = LoggerFactory.getLogger(CarController.class);

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAll() {
        return carService.findAll();
    }

    @GetMapping("/car/{id}")
    public Car getById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @GetMapping("/car")
    public List<Car> getByColor(@RequestParam("color") String color) {
        return carService.findByColor(color);
    }

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCar(@Valid @RequestBody CarRequest carRequest) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(carRequest);
        log.info("car request = " + s);
        log.info("start process...");
        log.warn("memory is running out...");
        log.error("process will be terminated...");
        carService.addCar(
                Car.builder()
                .name(carRequest.getName())
                .id(carRequest.getId())
                .color(carRequest.getColor())
                .build());
    }
}
