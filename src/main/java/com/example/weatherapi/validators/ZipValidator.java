package com.example.weatherapi.validators;

import com.example.weatherapi.validators.Impl.ZipValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static com.example.weatherapi.constants.DefaultConstants.ZIP_DEFAULT_MSG;

@Documented
@Constraint(validatedBy = ZipValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipValidator {

    String message() default ZIP_DEFAULT_MSG;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
