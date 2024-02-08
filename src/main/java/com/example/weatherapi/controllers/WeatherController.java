package com.example.weatherapi.controllers;

import com.example.weatherapi.dtos.ErrorDTO;
import com.example.weatherapi.dtos.WeatherResponseDTO;
import com.example.weatherapi.services.WeatherService;
import com.example.weatherapi.validators.ZipValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.weatherapi.constants.ApiConstants.*;
import static com.example.weatherapi.constants.DefaultConstants.CITY_PARAM;
import static com.example.weatherapi.constants.DefaultConstants.ZIP_PARAM;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            summary = GET_WEATHER_SUMMARY,
            description = GET_WEATHER_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            description = GET_WEATHER_RESPONSE_DESCRIPTION_S,
                            responseCode = OK,
                            content = @Content(mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = WeatherResponseDTO.class))
                    ),
                    @ApiResponse(
                            description = GET_WEATHER_RESPONSE_DESCRIPTION_BR,
                            responseCode = BAD_REQUEST,
                            content = @Content(mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = ErrorDTO.class))
                    ),
                    @ApiResponse(
                            description = GET_WEATHER_RESPONSE_DESCRIPTION_ISE,
                            responseCode = INTERNAL_SERVER_ERROR,
                            content = @Content(mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeatherDetails(
            @RequestParam(name = CITY_PARAM) String cityName,
            @RequestParam(name = ZIP_PARAM)
            @Valid @ZipValidator String zipCode) {
        return new ResponseEntity<>(weatherService.getWeatherData(cityName, zipCode),
                HttpStatus.OK);
    }
}
