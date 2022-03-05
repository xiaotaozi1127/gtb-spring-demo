package com.example.springbootdemo.userInterface.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @JsonIgnore
    private String invalid;
}
