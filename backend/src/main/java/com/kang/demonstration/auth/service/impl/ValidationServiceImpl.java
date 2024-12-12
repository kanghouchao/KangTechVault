package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.exception.EmailAlreadyRegisteredException;
import com.kang.demonstration.auth.service.UserService;
import com.kang.demonstration.auth.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author kanghouchao
 */
@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final UserService userService;

    private final MessageSource messageSource;

    @Override
    public void emailNotRegistered(String email, Locale locale) throws EmailAlreadyRegisteredException {
        if (this.userService.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException(
                    this.messageSource.getMessage("error.emailAlreadyRegistered", new Object[]{email}, Locale.getDefault()));
        }
    }
}
