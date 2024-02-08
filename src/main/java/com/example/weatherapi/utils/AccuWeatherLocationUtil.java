package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.AccuWeatherLocationDTO;
import com.example.weatherapi.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.example.weatherapi.constants.ErrorConstants.API_ERROR_MESSAGE;
import static com.example.weatherapi.constants.ErrorConstants.INVALID_CITY_MESSAGE;

@Component
@Slf4j
public class AccuWeatherLocationUtil {

    private final WebClient webClient;
    private final UrlConstants urlConstants;

    public AccuWeatherLocationUtil(WebClient webClient, UrlConstants urlConstants) {
        this.webClient = webClient;
        this.urlConstants = urlConstants;
    }

    public AccuWeatherLocationDTO getLocationDetails(String cityName) {
        System.out.println(urlConstants.getLocationURL(cityName));
        return webClient.get()
                .uri(urlConstants.getLocationURL(cityName))
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    log.error("HTTP error: {}", clientResponse.statusCode());
                    return Mono.error(new ApiException(API_ERROR_MESSAGE));
                })
                .bodyToMono(AccuWeatherLocationDTO[].class)
                .map(responseArray -> {
                            if (responseArray != null && responseArray.length > 0) {
                                return responseArray[0];
                            } else {
                                log.error("No location found for city: {}", cityName);
                                throw new IllegalArgumentException(INVALID_CITY_MESSAGE);
                            }
                        }
                )
                .block();
    }
}
