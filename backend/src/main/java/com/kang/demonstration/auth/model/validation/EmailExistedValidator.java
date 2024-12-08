package com.kang.demonstration.auth.model.validation;

import com.kang.demonstration.auth.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

/**
 * @author kanghouchao
 */
@AllArgsConstructor
public class EmailExistedValidator implements ConstraintValidator<Existed, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        return !this.userRepository.existsByEmail(email);
    }
}
