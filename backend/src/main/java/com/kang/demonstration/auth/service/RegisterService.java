package com.kang.demonstration.auth.service;

import jakarta.mail.MessagingException;

import java.util.Locale;

/**
 * @author kanghouchao
 */
public interface RegisterService {

    /**
     * send Email for register
     *
     * @param email  user email
     * @param locale location and lang
     * @throws MessagingException Mail Service ERROR
     */
    void sendEmail(String email, Locale locale) throws MessagingException;
}
