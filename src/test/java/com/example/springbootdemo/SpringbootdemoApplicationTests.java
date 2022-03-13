package com.example.springbootdemo;

import com.example.springbootdemo.userInterface.dto.request.CarRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootdemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_create_car_success() {
        var responseEntity =
                restTemplate.withBasicAuth("admin", "password123")
                        .postForEntity("/cars",
                        CarRequest.builder().name("volv").color("red").build(), CarRequest.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void should_create_car_failed_with_forbidden_error_for_normal_user() {
        var responseEntity =
                restTemplate.withBasicAuth("user", "password123")
                        .postForEntity("/cars",
                                CarRequest.builder().name("volv").color("red").build(), CarRequest.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
}
