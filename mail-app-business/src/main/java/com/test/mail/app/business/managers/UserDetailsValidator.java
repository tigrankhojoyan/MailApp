package com.test.mail.app.business.managers;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.exceptions.InvalidUserDetailsBusinessException;
import com.test.mail.app.dao.entities.UserDetails;
import org.apache.commons.validator.routines.LongValidator;
import org.joda.time.LocalDate;

import java.util.Calendar;

/**
 * Created by tigran on 12/14/16.
 */
public final class UserDetailsValidator {

    /**
     * Validates the details of the (userDetails parameter).
     *
     * @param userDetails
     * @throws BusinessException
     */
    public static void validateUserDetails(UserDetails userDetails) throws InvalidUserDetailsBusinessException {
        if(userDetails == null) {
            throw new InvalidUserDetailsBusinessException("UserDetails can't be NULL");
        }
//        validateId(userDetails.getUserDataId());
        validateBirthDate(userDetails.getBirthDate());
    }

    /**
     * Checks if the 'id' parameter is valid.
     *
     * @param id
     * @throws BusinessException
     */
    private static void validateId(Long id) throws InvalidUserDetailsBusinessException {
        if (id != null && !LongValidator.getInstance().isInRange(id, 0, Long.MAX_VALUE)) {
            throw new InvalidUserDetailsBusinessException(
                    String.format("The userID must be between %d and %d", 0, Long.MAX_VALUE));
        }
    }

    /**
     * Validates the age of user.
     *
     * @param date
     * @throws BusinessException
     */
    private static void validateBirthDate(LocalDate date) throws InvalidUserDetailsBusinessException {
        if(date != null && date.getYear() > Calendar.getInstance().get(Calendar.YEAR) - 10 ||
                date.getYear() < Calendar.getInstance().get(Calendar.YEAR) - 100) {
            throw new InvalidUserDetailsBusinessException(
                    String.format("Invalid age of user(%s)", date.toDate().toString()));
        }
    }
}
