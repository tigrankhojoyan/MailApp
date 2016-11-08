package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.enums.Gender;
import com.test.mail.app.dao.services.UserDao;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by tigran on 11/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDaoConfig.class})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    private static User testUser;
    private static UserDetails testUserDetails;


    @BeforeClass
    public static void setUp() {
        testUserDetails = new UserDetails(LocalDate.fromDateFields(new Date()), Gender.FEMALE);
        testUser = new User("testUserName", "testPassword",  testUserDetails);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUser() throws Exception {
        userDao.saveUser(testUser);
        User persistedUser = userDao.findByUserName("testUserName");
        Assert.assertEquals(testUser.getUserName(), persistedUser.getUserName());
        /*userDao.addUser(testUser);
        User resultUser = userDaoService.getUser(testUser.getUserName());
        Assert.assertEquals(testUser.getUserName(), resultUser.getUserName());*/
    }

    @Test
    public void testSaveUser() throws Exception {

    }

    @Test
    public void testFindAllUsers() throws Exception {

    }

    @Test
    public void testDeleteUserByUserName() throws Exception {

    }

    @Test
    public void testFindByUserName() throws Exception {

    }
}