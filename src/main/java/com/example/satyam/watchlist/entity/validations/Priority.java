package com.example.satyam.watchlist.entity.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriorityAnnotationLogic.class)
public @interface Priority {
    String message() default "Come on! Choose from L OR M OR H";

    Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default {};
}
