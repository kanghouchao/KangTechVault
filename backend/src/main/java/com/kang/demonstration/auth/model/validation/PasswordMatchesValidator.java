package com.kang.demonstration.auth.model.validation;

import com.kang.demonstration.auth.model.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author kanghouchao
 */
public class PasswordMatchesValidator implements ConstraintValidator<SamePassword, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }
        String password = request.password();
        String passwordConfirm = request.passwordConfirm();
        return password != null && password.equals(passwordConfirm);
    }
}
