package com.kang.demonstration.auth.exception;

import com.kang.demonstration.exception.ServiceException;

/**
 * @author kanghouchao
 */
public class TokenNotExistedException extends ServiceException {
    public TokenNotExistedException(String message) {
        super(message);
    }
}
