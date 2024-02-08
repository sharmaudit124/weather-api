package com.example.weatherapi.utils;

import com.example.weatherapi.constants.UrlConstants;
import com.example.weatherapi.dtos.OpenWeatherDataDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenWeatherDataUtilTest {

    @InjectMocks
    private OpenWeatherDataUtil openWeatherDataUtil;
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
    @DisplayName("Test for get open weather data")
    void getCurrentConditionTest() {
        String lat = "2622-4784";
        String lon = "28732-4843";
        String url = "mocked-url";
        var dto = OpenWeatherDataDTO.builder().build();

        when(urlConstants.getDataURL(any(), any())).thenReturn(url);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(OpenWeatherDataDTO.class)).thenReturn(Mono.just(dto));

        var result = openWeatherDataUtil.getOpenWeatherData(lat, lon);

        assertEquals(dto, result);
    }


}
