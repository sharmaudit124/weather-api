package com.example.weatherapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenWeatherDataDTO {
    private CoordDTO coord;
    private WeatherDTO[] weather;
    private MainDTO main;
    private int visibility;
    private WindDTO wind;
    private long dt;
    private SysDTO sys;
    private int timezone;
    private long id;
    private String name;
    private int cod;
}
