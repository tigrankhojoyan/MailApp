package com.test.mail.app.dao.services;

import com.test.mail.app.dao.conf.HibernateConfiguration;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserDetails;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.entities.enums.Gender;
import com.test.mail.app.dao.services.impl.UserDaoImpl;
import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by tigran on 11/10/16.
 */
@EnableTransactionManagement
public class Tester {

    public static void main(String[] args) {
        new Tester().tets();
    }

    public void tets() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(HibernateConfiguration.class);
        UserDao userDao = (UserDao)applicationContext.getBean("userDao");

        UserDetails testUserDetails = new UserDetails(LocalDate.fromDateFields(new Date()), Gender.MALE);
        User testUser = new User("testUserName1", "testPassword1",  testUserDetails);
        testUserDetails.setUser(testUser);

        UserDetails testUserDetails1 = new UserDetails(LocalDate.fromDateFields(new Date()), Gender.FEMALE);
        User testUser1 = new User("testUserName2", "testPassword2",  testUserDetails1);
        testUserDetails1.setUser(testUser1);

        userDao.saveUser(testUser);
        userDao.saveUser(testUser1);

        UserMusic userMusic1 = new UserMusic();
        userMusic1.setMusicName("music1");
        userMusic1.setMusicPath("/home/tigran/Downloads/01_selena_gomez_slow_down_myzuka.fm.mp3");

        UserMusic userMusic2 = new UserMusic();
        userMusic2.setMusicName("music2");
        userMusic2.setMusicPath("/home/tigran/Selena Gomez - Kill Them With Kindness (Mp3FB.com).mp3");

        userDao.addMusic("testUserName1", userMusic1);
        userDao.addMusic("testUserName2", userMusic1);

        userDao.addMusic("testUserName1", userMusic2);
        userDao.addMusic("testUserName2", userMusic2);

        List<User> users = userDao.findAllUsers();
        System.out.println("djdslkjdflmlfdmblfbm");

        //Duplicate user
        userDao.saveUser(testUser);
    }


}
