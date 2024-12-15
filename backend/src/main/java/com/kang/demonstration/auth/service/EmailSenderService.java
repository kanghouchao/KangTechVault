package com.kang.demonstration.auth.service;

import jakarta.mail.MessagingException;

/**
 * @author kanghouchao
 */
public interface EmailSenderService {

    /**
     * send email to user
     *
     * @param email user's email
     * @param token
     * @throws MessagingException
     */
    void sendEmailForRegister(String email, String token) throws MessagingException;

}
