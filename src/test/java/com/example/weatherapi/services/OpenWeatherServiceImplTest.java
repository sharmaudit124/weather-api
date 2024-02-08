package com.example.weatherapi.services;

import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.dtos.OpenWeatherGeoDTO;
import com.example.weatherapi.services.Impl.OpenWeatherServiceImpl;
import com.example.weatherapi.utils.OpenWeatherDataUtil;
import com.example.weatherapi.utils.OpenWeatherGeoUtil;
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
class OpenWeatherServiceImplTest {
    @InjectMocks
    private OpenWeatherServiceImpl openWeatherService;
    @Mock
    private OpenWeatherGeoUtil openWeatherGeoUtil;
    @Mock
    private OpenWeatherDataUtil openWeatherDataUtil;

    @Test
    @DisplayName("Test for get open weather data")
    void getOpenWeatherDataTest() {
        String zipCode = "143005";
        String countryCode = "IN";
        var dto = OpenWeatherGeoDTO.builder()
                .lat("lat")
                .lon("lon")
                .build();
        var res = OpenWeatherDataDTO.builder().build();

        when(openWeatherGeoUtil.getOpenWeatherGeo(any(), any())).thenReturn(dto);
        when(openWeatherDataUtil.getOpenWeatherData(any(), any())).thenReturn(res);

        assertEquals(res, openWeatherService.getOpenWeatherData(zipCode, countryCode));
    }
}
