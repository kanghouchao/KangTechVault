package com.kang.demonstration.exception;

/**
 * @author kanghouchao
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

}
