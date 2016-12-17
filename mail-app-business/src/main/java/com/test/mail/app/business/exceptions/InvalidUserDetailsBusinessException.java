package com.test.mail.app.business.exceptions;

/**
 * Created by tigran on 12/17/16.
 */
public class InvalidUserDetailsBusinessException extends BusinessException {

    public InvalidUserDetailsBusinessException() {
        super();
    }

    public InvalidUserDetailsBusinessException(String message) {
        super(message);
    }

    public InvalidUserDetailsBusinessException(String message, Throwable cause) {
        super(message, cause);
    }


    public InvalidUserDetailsBusinessException(Throwable cause) {
        super(cause);
    }
}
