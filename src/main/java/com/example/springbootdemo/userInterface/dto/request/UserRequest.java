package com.example.springbootdemo.userInterface.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRequest {
    private int age;
    private String username;
}
