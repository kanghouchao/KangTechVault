package com.kang.demonstration.auth.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kanghouchao
 */
@Constraint(validatedBy = EmailExistedValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Existed {

    String message() default "This email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
