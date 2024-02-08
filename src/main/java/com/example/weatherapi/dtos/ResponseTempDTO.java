package com.example.weatherapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseTempDTO {
    private double value;
    private String unit;
}
