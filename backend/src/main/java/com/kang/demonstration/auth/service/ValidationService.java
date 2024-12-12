package com.kang.demonstration.auth.service;

import com.kang.demonstration.auth.exception.EmailAlreadyRegisteredException;

import java.util.Locale;

/**
 * @author kanghouchao
 */
public interface ValidationService {

    /**
     * @param email
     * @param locale
     * @throws EmailAlreadyRegisteredException
     */
    void emailNotRegistered(String email, Locale locale) throws EmailAlreadyRegisteredException;
}
