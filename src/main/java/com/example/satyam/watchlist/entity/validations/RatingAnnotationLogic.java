package com.example.satyam.watchlist.entity.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingAnnotationLogic implements ConstraintValidator<Rating, Float> {
    public boolean isValid(Float value, ConstraintValidatorContext context){
        return value >= 0 && value <= 10;
    }
}
