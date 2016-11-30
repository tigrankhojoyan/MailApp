package com.test.mail.app.dao.exceptions;

/**
 * Created by tigran on 11/30/16.
 */
public class DaoException extends Exception {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }


    public DaoException(Throwable cause) {
        super(cause);
    }

}
