package com.kang.demonstration.auth.model.validation;

import com.kang.demonstration.auth.model.PasswordSettingRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author kanghouchao
 */
public class PasswordMatchesValidator implements ConstraintValidator<SamePassword, PasswordSettingRequest> {

    @Override
    public boolean isValid(PasswordSettingRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }
        String password = request.password();
        String passwordConfirm = request.passwordConfirm();
        return password != null && password.equals(passwordConfirm);
    }
}
