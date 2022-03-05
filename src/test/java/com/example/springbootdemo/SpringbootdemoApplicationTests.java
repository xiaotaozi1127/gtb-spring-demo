package com.example.springbootdemo;

import com.example.springbootdemo.Domain.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootdemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_get_car_by_id() {
        ResponseEntity<Car> responseEntity =
                restTemplate.getForEntity("/car/{id}", Car.class, 1L);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());

        Car car = responseEntity.getBody();
        assert car != null;
        Assertions.assertEquals("bmw", car.getName());
    }

}
