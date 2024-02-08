package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.AccuWeatherLocationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccuWeatherLocationUtilTest {

    @InjectMocks
    private AccuWeatherLocationUtil accuWeatherLocationUtil;
    @Mock
    private WebClient webClient;
    @Mock
    private UrlConstants urlConstants;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Test
    @DisplayName("Test for get current conditions")
    void getCurrentConditionTest() {
        String cityName = "Amritsar";
        String url = "some-url";
        var dto = AccuWeatherLocationDTO.builder().build();
        AccuWeatherLocationDTO dtoArr[] = {dto};

        when(urlConstants.getLocationURL(any())).thenReturn(url);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(AccuWeatherLocationDTO[].class)).thenReturn(Mono.just(dtoArr));

        var result = accuWeatherLocationUtil.getLocationDetails(cityName);

        assertEquals(dto, result);
    }

    @Test
    @DisplayName("Test for get current conditions when array is empty")
    void getCurrentConditionForEmptyArrayTest() {
        String cityName = "Amritsar";
        String url = "some-url";
        var dto = AccuWeatherLocationDTO.builder().build();
        AccuWeatherLocationDTO dtoArr[] = {};

        when(urlConstants.getLocationURL(any())).thenReturn(url);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(AccuWeatherLocationDTO[].class)).thenReturn(Mono.just(dtoArr));

        assertThrows(IllegalArgumentException.class, () -> {
            accuWeatherLocationUtil.getLocationDetails(cityName);
        });
    }
}
