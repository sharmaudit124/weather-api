package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.exceptions.ApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.example.weatherapi.constants.ErrorConstants.API_ERROR_MESSAGE;

@Component
public class AccuWeatherConditionUtil {
    private final WebClient webClient;
    private final UrlConstants urlConstants;

    public AccuWeatherConditionUtil(WebClient webClient, UrlConstants urlConstants) {
        this.webClient = webClient;
        this.urlConstants = urlConstants;
    }

    public AccuWeatherConditionDTO getCurrentConditions(String key) {
        return webClient.get()
                .uri(urlConstants.getConditionURL(key))
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        Mono.error(new ApiException(API_ERROR_MESSAGE)))
                .bodyToMono(AccuWeatherConditionDTO[].class)
                .map(responseArray ->
                        Optional.ofNullable(responseArray)
                                .filter(array -> array.length > 0)
                                .map(array -> array[0])
                                .orElse(null)
                )
                .block();
    }
}
