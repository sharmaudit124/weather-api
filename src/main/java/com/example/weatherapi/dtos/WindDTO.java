package com.example.weatherapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WindDTO {
    private double speed;
    private int deg;
    private double gust;
}
