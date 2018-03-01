package com.test.mail.app.dao.utils;

/**
 * Created by tigran on 11/27/16.
 */
public final class PatternConstants {

    public static final int USERNAME_MIN_LENGTH = 6;
    public static final int USERNAME_MAX_LENGTH = 14;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 14;

    public static final String USERNAME_PATTERN = "[a-zA-z0-9_]+";
    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)+";
    public static final String NAME_SURNAME_PATTERN = "[a-zA-z]{2,20}$";

    /**
     * Throws runtime exception when tries to init the class.
     */
    private PatternConstants() {
        throw new RuntimeException("Invalid operation: " +
                "It's not allowed to initialise final class containing only constants.");
    }
}
