package com.example.weatherapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponseDTO {
    @JsonProperty("WeatherText")
    private String weatherText;
    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;
    @JsonProperty("PrecipitationType")
    private String precipitationType;
    @JsonProperty("IsDayTime")
    private boolean isDayTime;
    @JsonProperty("Temperature")
    private ResponseTempDTO temperature;
    @JsonProperty("feels_like")
    private ResponseTempDTO feelsLike;
    private int pressure;
    private int humidity;
    private int visibility;
    private WindDTO wind;
    private long sunrise;
    private long sunset;
}
