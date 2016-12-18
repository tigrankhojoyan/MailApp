package com.test.mail.app.business.services;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.services.impl.UserBusinessServiceImpl;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.enums.Gender;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.dao.services.UserDao;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;

/**
 * Created by tigran on 12/17/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBusinessConfigs.class})
public class UserBusinessServiceTest {

    private UserDetails userDetails;

    @Autowired
    private UserBusinessServiceImpl businessService;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        LocalDate localeData =  new LocalDate(1993, 11, 11);
        userDetails = new UserDetails(localeData, Gender.MALE);
        userDetails.setUserDataId(1);
        MockitoAnnotations.initMocks(this);
        businessService.changeDao(userDao);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUser() throws DaoException, BusinessException {
        User user = new User("username1", "pasSWord1#");
        user.setUserDetails(userDetails);
        when(userDao.saveUser(user)).thenReturn(new Long(1));

        businessService.saveUser(user);

    }


}