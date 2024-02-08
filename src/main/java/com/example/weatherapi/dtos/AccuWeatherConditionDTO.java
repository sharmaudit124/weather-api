package com.example.weatherapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccuWeatherConditionDTO {
    @JsonProperty("WeatherText")
    private String weatherText;
    @JsonProperty("WeatherIcon")
    private int weatherIcon;
    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;
    @JsonProperty("PrecipitationType")
    private String precipitationType;
    @JsonProperty("IsDayTime")
    private boolean isDayTime;
    @JsonProperty("Temperature")
    private TemperatureDTO temperature;
}
