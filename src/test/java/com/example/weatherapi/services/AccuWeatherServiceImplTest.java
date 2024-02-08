package com.example.weatherapi.services;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.dtos.AccuWeatherLocationDTO;
import com.example.weatherapi.services.Impl.AccuWeatherServiceImpl;
import com.example.weatherapi.utils.AccuWeatherConditionUtil;
import com.example.weatherapi.utils.AccuWeatherLocationUtil;
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
class AccuWeatherServiceImplTest {

    @InjectMocks
    private AccuWeatherServiceImpl accuWeatherService;

    @Mock
    private AccuWeatherLocationUtil locationUtil;

    @Mock
    private AccuWeatherConditionUtil conditionUtil;

    @Test
    @DisplayName("Test for get accuweather data")
    void getAccuWeatherDataTest() {
        String cityName = "Amritsar";
        var dto = new AccuWeatherLocationDTO("key");
        var accuConditionDto = AccuWeatherConditionDTO.builder().build();
        when(locationUtil.getLocationDetails(any())).thenReturn(dto);
        when(conditionUtil.getCurrentConditions(any())).thenReturn(accuConditionDto);

        assertEquals(accuWeatherService.getAccuWeatherData(cityName), accuConditionDto);
    }

}
