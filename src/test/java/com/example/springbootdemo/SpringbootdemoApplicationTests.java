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
                restTemplate.postForEntity("/cars",
                        CarRequest.builder().name("volv").color("red").build(), CarRequest.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
