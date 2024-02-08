package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import com.example.weatherapi.exceptions.ApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.example.weatherapi.constants.ErrorConstants.API_ERROR_MESSAGE;

@Component
public class OpenWeatherDataUtil {
    private final WebClient webClient;
    private final UrlConstants urlConstants;

    public OpenWeatherDataUtil(WebClient webClient, UrlConstants urlConstants) {
        this.webClient = webClient;
        this.urlConstants = urlConstants;
    }

    public OpenWeatherDataDTO getOpenWeatherData(String lat, String lon) {
        return webClient.get()
                .uri(urlConstants.getDataURL(lat, lon))
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        Mono.error(new ApiException(API_ERROR_MESSAGE))
                )
                .bodyToMono(OpenWeatherDataDTO.class)
                .block();
    }
}
