package com.kang.demonstration.auth.service;

import com.kang.demonstration.auth.entity.EmailVerificationToken;

/**
 * @author kanghouchao
 */
public interface EmailVerificationTokenService {

    /**
     * create new token by email
     *
     * @param email email
     * @param token
     */
    EmailVerificationToken createNewToken(String email, String token);

    EmailVerificationToken findByToken(String token);
}
