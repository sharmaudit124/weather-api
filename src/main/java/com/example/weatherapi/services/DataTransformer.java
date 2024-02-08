package com.example.weatherapi.services;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.dtos.WeatherResponseDTO;

@FunctionalInterface
public interface DataTransformer {
    WeatherResponseDTO transformData(OpenWeatherDataDTO openWeatherData, AccuWeatherConditionDTO accuWeatherData);
}
