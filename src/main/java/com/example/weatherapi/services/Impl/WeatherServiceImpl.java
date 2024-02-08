package com.example.weatherapi.services.Impl;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.dtos.WeatherResponseDTO;
import com.example.weatherapi.services.AccuWeatherService;
import com.example.weatherapi.services.DataTransformer;
import com.example.weatherapi.services.OpenWeatherService;
import com.example.weatherapi.services.WeatherService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final AccuWeatherService accuWeatherService;
    private final OpenWeatherService openWeatherService;
    private final DataTransformer dataTransformService;


    public WeatherServiceImpl(AccuWeatherService accuWeatherService, OpenWeatherService openWeatherService, DataTransformer dataTransformService) {
        this.accuWeatherService = accuWeatherService;
        this.openWeatherService = openWeatherService;
        this.dataTransformService = dataTransformService;
    }

    @Override
    public WeatherResponseDTO getWeatherData(String cityName, String zip) {
        var array = zip.split(",");
        String zipCode = array[0];
        String countryCode = array[1];

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<AccuWeatherConditionDTO> accuWeatherData = CompletableFuture
                .supplyAsync(() -> accuWeatherService.getAccuWeatherData(cityName), executorService);
        CompletableFuture<OpenWeatherDataDTO> openWeatherData = CompletableFuture
                .supplyAsync(() -> openWeatherService.getOpenWeatherData(zipCode, countryCode), executorService);

        CompletableFuture.allOf(accuWeatherData, openWeatherData).join();

        WeatherResponseDTO response = dataTransformService.transformData(openWeatherData.join(), accuWeatherData.join());

        executorService.shutdown();
        return response;
    }
}
