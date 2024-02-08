package com.example.weatherapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenWeatherGeoDTO {
    private String zip;
    private String lat;
    private String lon;
    private String country;
}
