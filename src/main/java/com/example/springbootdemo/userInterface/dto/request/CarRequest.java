package com.example.springbootdemo.userInterface.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String color;
    private Long userId;
}
