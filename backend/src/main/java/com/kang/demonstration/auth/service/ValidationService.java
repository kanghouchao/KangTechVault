package com.kang.demonstration.auth.service;

import com.kang.demonstration.auth.exception.EmailAlreadyRegisteredException;

/**
 * @author kanghouchao
 */
public interface ValidationService {

    /**
     * @param email
     * @throws EmailAlreadyRegisteredException
     */
    void isEmailNotRegistered(String email) throws EmailAlreadyRegisteredException;

    void isTokenOK(String email, String token);
}
