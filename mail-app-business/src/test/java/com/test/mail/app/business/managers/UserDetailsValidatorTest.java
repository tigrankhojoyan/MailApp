package com.test.mail.app.business.managers;

import com.test.mail.app.business.exceptions.InvalidUserDetailsBusinessException;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.enums.Gender;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tigran on 12/17/16.
 */
public class UserDetailsValidatorTest {

    private UserDetails userDetails;

    @Before
    public void beforeMethod() {
        LocalDate localeData =  new LocalDate(1993, 11, 11);
        userDetails = new UserDetails(localeData, Gender.MALE);
        userDetails.setUserDataId(1);
    }

    @Test
    public void validateUserDetails() throws InvalidUserDetailsBusinessException {
        UserDetailsValidator.validateUserDetails(userDetails);
    }

    @Test(expected = InvalidUserDetailsBusinessException.class)
    public void validateUserDetailsInvalidId() throws InvalidUserDetailsBusinessException {
        userDetails.setUserDataId(-1);
        UserDetailsValidator.validateUserDetails(userDetails);
    }

    @Test(expected = InvalidUserDetailsBusinessException.class)
    public void validateUserDetailsInvalidBirthDate() throws InvalidUserDetailsBusinessException {
        userDetails.setBirthDate(new LocalDate());
        UserDetailsValidator.validateUserDetails(userDetails);
    }

    @Test(expected = InvalidUserDetailsBusinessException.class)
    public void validateUserDetailsNull() throws InvalidUserDetailsBusinessException {
        UserDetailsValidator.validateUserDetails(null);
    }

}