package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.entities.enums.Gender;
import com.test.mail.app.dao.services.UserDao;
import com.test.mail.app.dao.utils.SecurityUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

/**
 * Created by tigran on 11/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    private static User testUser;
    private static UserDetails testUserDetails;


    @Before
    public void setUp() {
        testUserDetails = new UserDetails(LocalDate.fromDateFields(new Date()), Gender.FEMALE);
        testUserDetails.setEmail("testmail@test.com");
        testUser = new User("testUserName", "test1Password", testUserDetails, "firstName", "LastName");
//        testUserDetails.setUser(testUser);
    }

    @After
    public void afterMethod() {

    }

//    @Test
//    public void aa() throws IOException {
//        byte[] byes = {97, 98, 99};
//
//        File file = new File("aa");
//        try (FileOutputStream fileWriter = new FileOutputStream(file)) {
//            fileWriter.write(byes);
//            fileWriter.close();
//            DataInputStream stream = new DataInputStream(new FileInputStream(file));
//            System.out.println("############################");
//            System.out.println(stream.readChar());
//
//            System.out.println("############################");
//            /*FileReader in = new FileReader(file) ;
//            BufferedReader reader = new BufferedReader(in, 500);
//            char[] chars = new char[2];
//            int numRead = 0;
//            while ((numRead = reader.read()) != -1) {
//                System.out.println("*******************");
//                System.out.println(numRead);
//                System.out.println("*******************");
//            }*/
//
//           /* in.read(chars);
//            for (char a: chars) {
//                System.out.println("*****************");
//                System.out.println(a);
//                System.out.println("*****************");
//            }*/
//
//                 }
//
///*        FileOutputStream fileOutputStream = new FileOutputStream(new File("aa"));
//        fileOutputStream.write(chars);
//        fileOutputStream.flush();
//        fileOutputStream.close();*/
//
//
///*for (char a: chars) {
//    System.out.println("*****************");
//    System.out.println(a);
//    System.out.println("*****************");
//}*/
//
//    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUser() throws Exception {
        userDao.saveUser(testUser);
        User persistedUser = userDao.findByUserName("testUserName");
        System.out.println(persistedUser);
        Assert.assertEquals(testUser.getUserName(), persistedUser.getUserName());
        Assert.assertEquals(testUser.getPassword(), persistedUser.getPassword());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdatePassword() throws Exception {
        userDao.saveUser(testUser);
        User persistedUser = userDao.findByUserName("testUserName");
        userDao.updateUserPassword("testUserName", "test1Password", "test2Password");
        System.out.println(persistedUser);
        Assert.assertEquals(testUser.getUserName(), persistedUser.getUserName());
        Assert.assertEquals("test2Password", persistedUser.getPassword());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testAddMusic() throws Exception {
        userDao.saveUser(testUser);
        User persistedUser = userDao.findByUserName("testUserName");
        Assert.assertEquals(testUser.getUserName(), persistedUser.getUserName());
        UserMusic music = new UserMusic();
        music.setMusicName("testMusic");
        music.setMusicPath("/home/tigran/Downloads/01_selena_gomez_slow_down_myzuka.fm.mp3");
        userDao.addMusic("testUserName", music);
        persistedUser = userDao.findByUserName("testUserName");
        Assert.assertEquals("testMusic", persistedUser.getUserMusics().get(0).getMusicName());
        List<UserMusic> userMusics = userDao.getUserMusics("testUserName");
        Assert.assertEquals(1, userMusics.size());
        Assert.assertEquals(music, userMusics.get(0));

    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Rollback(true)
    public void testSaveUserUsingDuplicateUserNames() throws Exception {
        userDao.saveUser(testUser);
        User duplicateUser = new User("testUserName", "testPassword2", testUserDetails, "FNDup", "LNDup");
        ;
        userDao.saveUser(duplicateUser);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllUsers() throws Exception {
        UserDetails testUserDetails2 = new UserDetails(LocalDate.fromDateFields(new Date()), Gender.FEMALE);
        testUserDetails2.setEmail("test2mail@test.com");
        UserDetails testUserDetails3 = new UserDetails(LocalDate.fromDateFields(new Date()), Gender.MALE);
        testUserDetails3.setEmail("test3mail@test.com");
        User testUser2 = new User("testUser2", "testPassword2", testUserDetails2, "FirstNametw", "LAstNametw");
        User testUser3 = new User("testUser3", "testPassword3", testUserDetails3, "FirstNameth", "LAstNameth");
//        testUserDetails2.setUser(testUser2);
//        testUserDetails3.setUser(testUser3);
        testUserDetails2.setUser(testUser2);
        testUserDetails3.setUser(testUser3);

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
    @Transactional
    @Rollback(true)
    public void testDeleteUserByUserName() throws Exception {
        userDao.saveUser(testUser);
        userDao.deleteUserByUserName("testUserName");
        List<User> users = userDao.findAllUsers();
        Assert.assertEquals(0, users.size());
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