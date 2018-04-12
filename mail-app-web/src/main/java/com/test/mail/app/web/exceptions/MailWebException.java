package com.test.mail.app.web.exceptions;

/**
 * Created by tigran on 1/3/17.
 */
public class MailWebException extends Exception{

    public MailWebException() {
        super();
    }

    public MailWebException(String message) {
        super(message);
    }

    public MailWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailWebException(Throwable cause) {
        super(cause);
    }
}
