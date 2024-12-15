package com.kang.demonstration.auth.exception;

import com.kang.demonstration.exception.ServiceException;

/**
 * @author kanghouchao
 */
public class TokenHasExpiredException extends ServiceException {
    public TokenHasExpiredException(String message) {
        super(message);
    }
}
