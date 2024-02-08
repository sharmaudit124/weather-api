package com.example.weatherapi.services.Impl;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.dtos.ResponseTempDTO;
import com.example.weatherapi.dtos.WeatherResponseDTO;
import com.example.weatherapi.services.DataTransformer;
import org.springframework.stereotype.Component;

import static com.example.weatherapi.constants.TemperatureConstants.CELSIUS;
import static com.example.weatherapi.constants.TemperatureConstants.KELVIN_CONSTANT;

@Component
public class DataTransformServiceImpl implements DataTransformer {
    @Override
    public WeatherResponseDTO transformData(OpenWeatherDataDTO openWeatherData, AccuWeatherConditionDTO accuWeatherData) {
        return WeatherResponseDTO.builder()
                .weatherText(accuWeatherData.getWeatherText())
                .hasPrecipitation(accuWeatherData.isHasPrecipitation())
                .precipitationType(accuWeatherData.getPrecipitationType())
                .isDayTime(accuWeatherData.isDayTime())
                .temperature(new ResponseTempDTO(accuWeatherData
                        .getTemperature()
                        .getMetric()
                        .getValue(), accuWeatherData
                        .getTemperature()
                        .getMetric()
                        .getUnit()))
                .feelsLike(new ResponseTempDTO(getTemperatureInCelsius(
                        openWeatherData.getMain()
                                .getFeelsLike())
                        , CELSIUS)
                )
                .pressure(openWeatherData.getMain().getPressure())
                .humidity(openWeatherData.getMain().getHumidity())
                .visibility(openWeatherData.getVisibility())
                .wind(openWeatherData.getWind())
                .sunrise(openWeatherData.getSys().getSunrise())
                .sunset(openWeatherData.getSys().getSunset())
                .build();
    }

    private Double getTemperatureInCelsius(Double temp) {
        return temp - KELVIN_CONSTANT;
    }
}
