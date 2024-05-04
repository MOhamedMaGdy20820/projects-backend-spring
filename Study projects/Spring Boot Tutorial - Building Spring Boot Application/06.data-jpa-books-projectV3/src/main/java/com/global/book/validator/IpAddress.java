package com.global.book.validator;

import jakarta.validation.Payload;
import jakarta.validation.Constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = {IpAddressImpl.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface IpAddress {

    String message() default "{validation.constraints.ip-address.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}