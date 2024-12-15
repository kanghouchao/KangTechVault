package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.entity.EmailVerificationToken;
import com.kang.demonstration.auth.exception.EmailAlreadyRegisteredException;
import com.kang.demonstration.auth.exception.TokenHasExpiredException;
import com.kang.demonstration.auth.exception.TokenHasUsedException;
import com.kang.demonstration.auth.exception.TokenNotExistedException;
import com.kang.demonstration.auth.service.EmailVerificationTokenService;
import com.kang.demonstration.auth.service.UserService;
import com.kang.demonstration.auth.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author kanghouchao
 */
@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final UserService userService;

    private final EmailVerificationTokenService tokenService;

    private final MessageSource messageSource;

    @Override
    public void isEmailNotRegistered(String email) throws EmailAlreadyRegisteredException {
        if (this.userService.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException(
                    this.messageSource.getMessage("error.EmailAlreadyRegisteredException",
                            new Object[]{email},
                            LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public void isTokenOK(String email, String token) {
        EmailVerificationToken verificationToken = this.tokenService.findByToken(token);
        if (verificationToken == null
                || !verificationToken.getEmail().equals(email)) {
            throw new TokenNotExistedException(
                    this.messageSource.getMessage("error.TokenNotExistedException",
                            null, LocaleContextHolder.getLocale()));
        } else if (verificationToken.getIsUsed()) {
            throw new TokenHasUsedException(
                    this.messageSource.getMessage("error.TokenHasUsedException",
                            null, LocaleContextHolder.getLocale())
            );
        } else if (verificationToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new TokenHasExpiredException(
                    this.messageSource.getMessage("error.TokenHasExpiredException",
                            null, LocaleContextHolder.getLocale())
            );
        }
    }
}
