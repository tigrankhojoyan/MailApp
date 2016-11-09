package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.enums.Gender;
import com.test.mail.app.dao.services.UserDao;
import org.joda.time.LocalDate;
import org.junit.After;
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
import java.util.List;

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
        testUserDetails.setUser(testUser);
    }

    @After
    public void afterMethod() {

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUser() throws Exception {
        userDao.saveUser(testUser);
        User persistedUser = userDao.findByUserName("testUserName");
        Assert.assertEquals(testUser.getUserName(), persistedUser.getUserName());
        Assert.assertEquals(testUser.getPassword(), persistedUser.getPassword());
    }

    @Test
    public void testSaveUser() throws Exception {

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllUsers() throws Exception {
        User testUser2 = new User("testUser2", "testPassword2", testUserDetails);
        User testUser3 = new User("testUser3", "testPassword3", testUserDetails);

        userDao.saveUser(testUser);
        userDao.saveUser(testUser2);
        userDao.saveUser(testUser3);

        List<User> users = userDao.findAllUsers();
        Assert.assertEquals(3, users.size());
        Assert.assertEquals("testUserName", users.get(0).getUserName());
        Assert.assertEquals("testUser2", users.get(1).getUserName());
        Assert.assertEquals("testUser3", users.get(2).getUserName());
    }

    @Test
    public void testDeleteUserByUserName() throws Exception {

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByUserName() throws Exception {
        userDao.saveUser(testUser);
        User persistedUser = userDao.findByUserName("testUserName");
        Assert.assertEquals(testUser.getUserName(), persistedUser.getUserName());
        userDao.deleteUserByUserName("testUserName");
        List<User> users = userDao.findAllUsers();
        Assert.assertEquals(0, users.size());
    }
}