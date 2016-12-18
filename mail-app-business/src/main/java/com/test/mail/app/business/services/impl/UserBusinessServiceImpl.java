package com.test.mail.app.business.services.impl;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.managers.UserMusicValidator;
import com.test.mail.app.business.managers.UserValidator;
import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.dao.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tigran on 12/13/16.
 */
@Service("userBusinessService")
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDao userDao;

    public UserBusinessServiceImpl() {

    }

    public UserBusinessServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long saveUser(User user) throws BusinessException, DaoException {
        UserValidator.validateUser(user);
        return userDao.saveUser(user);
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        return userDao.findAllUsers();
    }

    @Override
    public void deleteUserByUserName(String userName) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        userDao.deleteUserByUserName(userName);
    }

    @Override
    public User findByUserName(String userName) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        return userDao.findByUserName(userName);
    }

    @Override
    public void addMusic(String userName, UserMusic music) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        UserMusicValidator.validateMusic(music);//TODO implement the method
        userDao.addMusic(userName, music);
    }

    @Override
    public List<UserMusic> getUserMusics(String userName) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        return userDao.getUserMusics(userName);
    }

    public void changeDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
