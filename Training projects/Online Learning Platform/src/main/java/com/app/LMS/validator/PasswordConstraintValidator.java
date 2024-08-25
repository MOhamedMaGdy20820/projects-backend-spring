package com.app.LMS.validator;

import com.app.LMS.validator.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String SPECIAL_CHAR_REGEX = ".*[!@#$%^&*()-_=+{};:,<.>].*";

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }

        if (!Pattern.matches(SPECIAL_CHAR_REGEX, password)) {
            return false;
        }

        return true;
    }
}
