package com.kang.demonstration.auth.exception;

import com.kang.demonstration.exception.ServiceException;

/**
 * @author kanghouchao
 */
public class EmailAlreadyRegisteredException extends ServiceException {

    /**
     * @param message
     */
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
