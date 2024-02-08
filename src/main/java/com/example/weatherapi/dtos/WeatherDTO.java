package com.example.weatherapi.dtos;

import lombok.Data;

@Data
public class WeatherDTO {
    private int id;
    private String main;
    private String description;
    private String icon;
}
