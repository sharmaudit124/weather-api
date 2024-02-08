package com.example.weatherapi.services;

import com.example.weatherapi.dtos.WeatherResponseDTO;

public interface WeatherService {
    WeatherResponseDTO getWeatherData(String cityName, String zip);
}
