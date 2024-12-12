package com.kang.demonstration.auth.exception;

/**
 * @author kanghouchao
 */
public class EmailAlreadyRegisteredException extends RuntimeException {

    /**
     *
     * @param message
     */
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
