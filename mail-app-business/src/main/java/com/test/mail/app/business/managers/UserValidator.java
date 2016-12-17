package com.test.mail.app.business.managers;

import com.test.mail.app.business.exceptions.InvalidUserBusinessException;
import com.test.mail.app.business.exceptions.InvalidUserDetailsBusinessException;
import com.test.mail.app.dao.entities.User;
import org.apache.commons.validator.routines.LongValidator;
import org.apache.commons.validator.routines.RegexValidator;

/**
 * Created by tigran on 12/14/16.
 */
public final class UserValidator {

    private static final String USER_NAME_PATTERN = "^[a-z0-9_-]{6,14}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static void validateUser(User user) throws InvalidUserBusinessException,
            InvalidUserDetailsBusinessException {
        if(user == null) {
            throw new InvalidUserBusinessException("The 'user' data can't be NULL");
        }
        validateUserId(user.getUserId());
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
        UserDetailsValidator.validateUserDetails(user.getUserDetails());
    }

    public static void validateUserName(String userName) throws InvalidUserBusinessException {
        if(!(new RegexValidator(USER_NAME_PATTERN).isValid(userName))) {
            throw new InvalidUserBusinessException(String.format("Invalid userName - %s", userName));
        }
    }

    public static void validatePassword(String password) throws InvalidUserBusinessException {
        if(!(new RegexValidator(PASSWORD_PATTERN).isValid(password))) {
            throw new InvalidUserBusinessException(String.format("Invalid password - %s", password));
        }
    }

    public static void validateUserId(Long id) throws InvalidUserBusinessException {
        if (!LongValidator.getInstance().isInRange(id, 0, Long.MAX_VALUE)) {
            throw new InvalidUserBusinessException(
                    String.format("The userID must be between %d and %d", 0, Long.MAX_VALUE));
        }
    }

}
