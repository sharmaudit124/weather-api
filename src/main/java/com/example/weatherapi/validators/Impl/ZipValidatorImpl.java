package com.example.weatherapi.validators.Impl;

import com.example.weatherapi.validators.ZipValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.example.weatherapi.constants.DefaultConstants.ZIP_DEFAULT_MSG;

public class ZipValidatorImpl implements ConstraintValidator<ZipValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String arr[] = value.trim().split(",");
        if (arr.length == 2) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(ZIP_DEFAULT_MSG).addConstraintViolation();
        return false;
    }
}
