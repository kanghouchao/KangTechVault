package com.kang.demonstration.auth.service;

import com.kang.demonstration.auth.entity.User;
import jakarta.mail.MessagingException;

/**
 * @author kanghouchao
 */
public interface RegisterService {

    /**
     * send Email for register
     *
     * @param email user email
     * @throws MessagingException Mail Service ERROR
     */
    void sendEmail(String email) throws MessagingException;

    User createNewUser(String email, String token, String password);
}
