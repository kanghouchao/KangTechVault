package com.kang.demonstration.auth.service;

import jakarta.mail.MessagingException;

/**
 * @author kanghouchao
 */
public interface RegisterService {

    void sendEmail(String email) throws MessagingException;
}
