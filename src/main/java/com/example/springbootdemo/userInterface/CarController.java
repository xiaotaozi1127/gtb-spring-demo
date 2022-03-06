package com.example.springbootdemo.userInterface;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.application.CarService;
import com.example.springbootdemo.userInterface.dto.request.CarRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @GetMapping("/color")
    public List<Car> getByColor(@RequestParam("color") String color) {
        return carService.findByColor(color);
    }

    @PostMapping
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
