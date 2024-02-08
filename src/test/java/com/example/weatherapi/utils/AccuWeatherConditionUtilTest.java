package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.AccuWeatherConditionDTO;
import com.example.weatherapi.exceptions.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.example.weatherapi.constants.ErrorConstants.API_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccuWeatherConditionUtilTest {

    @InjectMocks
    private AccuWeatherConditionUtil conditionUtil;
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
        String key = "15462";
        String url = "some-url";
        var dto = AccuWeatherConditionDTO.builder().build();
        AccuWeatherConditionDTO dtoArr[] = {dto};

        when(urlConstants.getConditionURL(any())).thenReturn(url);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(AccuWeatherConditionDTO[].class)).thenReturn(Mono.just(dtoArr));

        var result = conditionUtil.getCurrentConditions(key);

        assertEquals(dto, result);
    }

}
