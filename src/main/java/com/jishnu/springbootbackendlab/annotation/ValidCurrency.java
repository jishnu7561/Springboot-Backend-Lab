package com.jishnu.springbootbackendlab.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Where to be used
@Retention(RetentionPolicy.RUNTIME) // How log it will exist
public @interface ValidCurrency {

    String message() default "Not a valid currency";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
