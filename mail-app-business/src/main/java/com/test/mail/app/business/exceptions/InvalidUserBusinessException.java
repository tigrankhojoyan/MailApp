package com.test.mail.app.business.exceptions;

/**
 * Created by tigran on 12/17/16.
 */
public class InvalidUserBusinessException extends BusinessException {
    public InvalidUserBusinessException() {
        super();
    }

    public InvalidUserBusinessException(String message) {
        super(message);
    }

    public InvalidUserBusinessException(String message, Throwable cause) {
        super(message, cause);
    }


    public InvalidUserBusinessException(Throwable cause) {
        super(cause);
    }
}
