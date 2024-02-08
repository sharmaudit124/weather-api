package com.example.weatherapi.dtos;

import lombok.Data;

@Data
public class SysDTO {
    private String country;
    private long sunrise;
    private long sunset;
}
