package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.OpenWeatherGeoDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.example.weatherapi.constants.ErrorConstants.INVALID_CODE_MESSAGE;

@Component
public class OpenWeatherGeoUtil {
    private final WebClient webClient;
    private final UrlConstants urlConstants;

    public OpenWeatherGeoUtil(WebClient webClient, UrlConstants urlConstants) {
        this.webClient = webClient;
        this.urlConstants = urlConstants;
    }

    public OpenWeatherGeoDTO getOpenWeatherGeo(String zipCode, String countryCode) {
        return webClient.get()
                .uri(urlConstants.getGeoURL(zipCode, countryCode))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new IllegalArgumentException(INVALID_CODE_MESSAGE)))
                .bodyToMono(OpenWeatherGeoDTO.class)
                .block();
    }
}
