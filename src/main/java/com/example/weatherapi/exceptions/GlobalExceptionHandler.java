package com.example.weatherapi.exceptions;

import com.example.weatherapi.dtos.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleBadRequest(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()
                , LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleValidationException(ConstraintViolationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getConstraintViolations()
                .stream().toList().get(0).getMessageTemplate(), LocalDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CompletionException.class})
    public ResponseEntity<ErrorDTO> handleError(Exception e) {
        Throwable cause = e.getCause();
        if (cause instanceof IllegalArgumentException) {
            return handleBadRequest((IllegalArgumentException) cause);
        } else {
            return handleInternalServerError((Exception) cause);
        }
    }

    @ExceptionHandler({ApiException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleInternalServerError(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now())
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
