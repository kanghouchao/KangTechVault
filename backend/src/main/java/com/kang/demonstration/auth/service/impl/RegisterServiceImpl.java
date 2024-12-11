package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.service.EmailService;
import com.kang.demonstration.auth.service.EmailVerificationTokenService;
import com.kang.demonstration.auth.service.RegisterService;
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

    private final EmailService emailService;

    private final EmailVerificationTokenService tokenService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmail(final String email) throws MessagingException {
        final String token = UUID.randomUUID().toString();
        this.tokenService.createNewToken(email, token);
        //TODO this Locale should get from frontend
        this.emailService.sendEmailForRegister(email, token, Locale.getDefault());
    }
}
