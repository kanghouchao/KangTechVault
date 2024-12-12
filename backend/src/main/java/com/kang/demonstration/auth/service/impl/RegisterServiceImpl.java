package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.service.EmailSenderService;
import com.kang.demonstration.auth.service.EmailVerificationTokenService;
import com.kang.demonstration.auth.service.RegisterService;
import com.kang.demonstration.auth.service.ValidationService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

/**
 * @author kanghouchao
 */
@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final EmailSenderService emailSenderService;

    private final EmailVerificationTokenService tokenService;

    private final ValidationService validationService;

    @Override
    @Transactional
    public void sendEmail(final String email, Locale locale) throws MessagingException {
        this.validationService.emailNotRegistered(email, locale);
        final String token = UUID.randomUUID().toString();
        this.tokenService.createNewToken(email, token);
        this.emailSenderService.sendEmailForRegister(email, token, locale);
    }
}
