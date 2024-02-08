package com.example.weatherapi.services.Impl;

import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.dtos.OpenWeatherGeoDTO;
import com.example.weatherapi.services.OpenWeatherService;
import com.example.weatherapi.utils.OpenWeatherDataUtil;
import com.example.weatherapi.utils.OpenWeatherGeoUtil;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {
    private final OpenWeatherGeoUtil openWeatherGeoUtil;
    private final OpenWeatherDataUtil openWeatherDataUtil;

    public OpenWeatherServiceImpl(OpenWeatherGeoUtil openWeatherGeoUtil, OpenWeatherDataUtil openWeatherDataUtil) {
        this.openWeatherGeoUtil = openWeatherGeoUtil;
        this.openWeatherDataUtil = openWeatherDataUtil;
    }

    @Override
    public OpenWeatherDataDTO getOpenWeatherData(String zipCode, String countryCode) {
        OpenWeatherGeoDTO openWeatherGeo = openWeatherGeoUtil.getOpenWeatherGeo(zipCode, countryCode);
        return openWeatherDataUtil.getOpenWeatherData(openWeatherGeo.getLat(), openWeatherGeo.getLon());
    }
}
