package com.example.satyam.watchlist.entity.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityAnnotationLogic implements ConstraintValidator<Priority, String> { //it is a abstract method which has no body

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.trim().length() == 1 && "LHM".contains(value.trim());
    }
}
