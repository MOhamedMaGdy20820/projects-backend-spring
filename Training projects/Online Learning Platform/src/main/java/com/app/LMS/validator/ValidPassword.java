package com.app.LMS.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = {PasswordConstraintValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "Passwords must meet security standards, have a minimum length of 8 and include special characters.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}