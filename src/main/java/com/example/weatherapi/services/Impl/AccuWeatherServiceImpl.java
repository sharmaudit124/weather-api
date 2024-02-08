package com.example.weatherapi.services.Impl;

import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.services.AccuWeatherService;
import com.example.weatherapi.utils.AccuWeatherConditionUtil;
import com.example.weatherapi.utils.AccuWeatherLocationUtil;
import org.springframework.stereotype.Service;

@Service
public class AccuWeatherServiceImpl implements AccuWeatherService {
    private final AccuWeatherLocationUtil accuWeatherLocationUtil;
    private final AccuWeatherConditionUtil accuWeatherConditionUtil;

    public AccuWeatherServiceImpl(AccuWeatherLocationUtil accuWeatherLocationUtil, AccuWeatherConditionUtil accuWeatherConditionUtil) {
        this.accuWeatherLocationUtil = accuWeatherLocationUtil;
        this.accuWeatherConditionUtil = accuWeatherConditionUtil;
    }

    @Override
    public AccuWeatherConditionDTO getAccuWeatherData(String cityName) {
        String key = accuWeatherLocationUtil.getLocationDetails(cityName).getKey();
        return accuWeatherConditionUtil.getCurrentConditions(key);
    }
}
