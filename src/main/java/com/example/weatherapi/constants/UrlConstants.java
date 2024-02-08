package com.example.weatherapi.constants;

import com.example.weatherapi.config.WeatherApisProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlConstants {
    private static final String LOCATION_QUERY_PARAM = "q";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String ZIP_QUERY_PARAM = "zip";
    private static final String LAT_QUERY_PARAM = "lat";
    private static final String LON_QUERY_PARAM = "lon";
    private static final String APP_ID_QUERY_PARAM = "appid";
    private static final String LOCATION_BASE_URL = "https://dataservice.accuweather.com/locations/v1/search";
    private static final String CONDITION_BASE_URL = "https://dataservice.accuweather.com/currentconditions/v1/";
    private static final String GEO_BASE_URL = "https://api.openweathermap.org/geo/1.0/zip";
    private static final String OPEN_WEATHER_DATA_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final WeatherApisProperties properties;

    public UrlConstants(WeatherApisProperties properties) {
        this.properties = properties;
    }

    public final String getLocationURL(String cityName) {
        return UriComponentsBuilder.fromUriString(LOCATION_BASE_URL)
                .queryParam(LOCATION_QUERY_PARAM, cityName)
                .queryParam(API_KEY_QUERY_PARAM, properties.getAccuWeatherApiKey())
                .buildAndExpand()
                .toUriString();
    }

    public final String getConditionURL(String key) {
        return UriComponentsBuilder.fromUriString(CONDITION_BASE_URL + key)
                .queryParam(API_KEY_QUERY_PARAM, properties.getAccuWeatherApiKey())
                .buildAndExpand()
                .toUriString();
    }

    public final String getGeoURL(String zipCode, String countryCode) {
        return UriComponentsBuilder.fromUriString(GEO_BASE_URL)
                .queryParam(ZIP_QUERY_PARAM, zipCode + "," + countryCode)
                .queryParam(APP_ID_QUERY_PARAM, properties.getOpenWeatherApiKey())
                .buildAndExpand()
                .toUriString();
    }

    public final String getDataURL(String lat, String lon) {
        return UriComponentsBuilder.fromUriString(OPEN_WEATHER_DATA_BASE_URL)
                .queryParam(LAT_QUERY_PARAM, lat)
                .queryParam(LON_QUERY_PARAM, lon)
                .queryParam(APP_ID_QUERY_PARAM, properties.getOpenWeatherApiKey())
                .buildAndExpand()
                .toUriString();
    }
}
