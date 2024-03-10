package com.example.satyam.watchlist.entity.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingAnnotationLogic.class)
public @interface Rating {
    String message() default "Please Enter Value between 0 to 10";

    Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default {};
}
