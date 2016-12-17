package com.test.mail.app.business.managers;


import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.exceptions.InvalidUserBusinessException;
import com.test.mail.app.business.exceptions.InvalidUserDetailsBusinessException;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.enums.Gender;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tigran on 12/17/16.
 */
public class UserValidatorTest {

    private UserDetails userDetails;

    @Before
    public void beforeMethod() {
        LocalDate localeData =  new LocalDate(1993, 11, 11);
        userDetails = new UserDetails(localeData, Gender.MALE);
        userDetails.setUserDataId(1);
    }

    @Test
    public void testValidUser() throws BusinessException {
        User user = new User("username1", "pasSWord1#");
        user.setUserId(new Long(1));

        user.setUserDetails(userDetails);

        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUserBusinessException.class)
    public void testInvalidUserByUserId() throws BusinessException {
        User user = new User("Invalid", "paSSword#1In");
        user.setUserId(new Long(-1));
        UserValidator.validateUser(user);
    }


    @Test(expected = InvalidUserBusinessException.class)
    public void testInvalidUserByUserName() throws BusinessException {
        User user = new User("Invalid", "paSSword#1In");
        user.setUserId(new Long(1));
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUserBusinessException.class)
    public void testInvalidUserByPassword() throws BusinessException {
        User user = new User("username", "paSSwor ");
        user.setUserId(new Long(1));
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUserBusinessException.class)
    public void testInvalidUserNull() throws BusinessException {
        UserValidator.validateUser(null);
    }

    @Test(expected = InvalidUserDetailsBusinessException.class)
    public void testInvalidUserByNullUserDetails() throws BusinessException {
        User user = new User("username1", "pasSWord1#");
        user.setUserId(new Long(1));

        UserValidator.validateUser(user);
    }

}