package com.kang.demonstration.auth.service;

import jakarta.mail.MessagingException;

import java.util.Locale;

/**
 * @author kanghouchao
 */
public interface EmailService {

    /**
     * send email to user
     *
     * @param email user's email
     * @param token
     * @throws MessagingException
     */
    void sendEmailForRegister(String email, String token, Locale locale) throws MessagingException;

}
