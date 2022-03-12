package com.example.springbootdemo.userInterface;

import com.example.springbootdemo.Domain.Car;
import com.example.springbootdemo.application.CarService;
import com.example.springbootdemo.userInterface.dto.request.CarRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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

    @GetMapping("/all")
    public List<Car> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(item -> item.getAuthority().contains("admin"))) {
            return carService.findAll();
        }
       throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "you are not authorized to view all cars");
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @GetMapping
    public List<Car> getByColor(@RequestParam("color") String color) {
        return carService.findByColor(color);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCar(@Valid @RequestBody CarRequest carRequest) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(carRequest);
        log.info("car request = " + s);
        carService.addCar(carRequest.getUserId(),
                Car.builder()
                        .name(carRequest.getName())
                        .color(carRequest.getColor())
                        .build());
    }
}
