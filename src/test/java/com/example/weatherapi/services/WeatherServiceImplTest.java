package com.example.weatherapi.services;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.dtos.WeatherResponseDTO;
import com.example.weatherapi.services.Impl.WeatherServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Mock
    private AccuWeatherService accuWeatherService;
    @Mock
    private OpenWeatherService openWeatherService;
    @Mock
    private DataTransformer dataTransformService;

    @Test
    @DisplayName("Test for get weather data")
    void getWeatherDataTest() {
        String cityName = "Amritsar";
        String zipCode = "143001,IN";

        var accWeatherDTO = AccuWeatherConditionDTO.builder().build();
        var openWeatherDTO = OpenWeatherDataDTO.builder().build();
        var response = WeatherResponseDTO.builder().build();

        when(accuWeatherService.getAccuWeatherData(any())).thenReturn(accWeatherDTO);
        when(openWeatherService.getOpenWeatherData(any(), any())).thenReturn(openWeatherDTO);
        when(dataTransformService.transformData(any(), any())).thenReturn(response);

        assertEquals(response, weatherService.getWeatherData(cityName, zipCode));

    }
}
