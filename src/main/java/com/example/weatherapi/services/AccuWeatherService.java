package com.example.weatherapi.services;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;

public interface AccuWeatherService {
    AccuWeatherConditionDTO getAccuWeatherData(String cityName);
}
