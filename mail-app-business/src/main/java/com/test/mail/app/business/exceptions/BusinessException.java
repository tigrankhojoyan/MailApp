package com.test.mail.app.business.exceptions;

/**
 * Created by tigran on 12/13/16.
 */
public class BusinessException extends Exception {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }


    public BusinessException(Throwable cause) {
        super(cause);
    }
}
