package com.example.weatherapi.services;

import com.example.weatherapi.dtos.OpenWeatherDataDTO;

public interface OpenWeatherService {
    OpenWeatherDataDTO getOpenWeatherData(String zipCode, String countryCode);
}
