package com.example.weatherapi.controllers;

import com.example.weatherapi.dtos.WeatherResponseDTO;
import com.example.weatherapi.services.Impl.WeatherServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private WeatherServiceImpl weatherService;

    @Test
    @DisplayName("Test for get weather details")
    void getWeatherDetailsTest() {
        String cityName = "Amritsar";
        String zipCode = "143005";
        var dto = WeatherResponseDTO.builder().build();

        when(weatherService.getWeatherData(any(), any()))
                .thenReturn(dto);

        var response = weatherController.getWeatherDetails(cityName, zipCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
}
