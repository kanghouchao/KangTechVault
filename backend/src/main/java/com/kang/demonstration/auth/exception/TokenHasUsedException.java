package com.kang.demonstration.auth.exception;

import com.kang.demonstration.exception.ServiceException;

/**
 * @author kanghouchao
 */
public class TokenHasUsedException extends ServiceException {
    public TokenHasUsedException(String message) {
        super(message);
    }
}
