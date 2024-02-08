package com.example.weatherapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureDTO {
    @JsonProperty("Metric")
    private MetricDTO metric;
}
