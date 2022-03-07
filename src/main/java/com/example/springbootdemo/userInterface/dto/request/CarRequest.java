package com.example.springbootdemo.userInterface.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    @NotNull(message = "car id cannot be empty")
    @Min(value = 1, message = "illegal card id")
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String color;
    @NotBlank
    private Long userId;
}
