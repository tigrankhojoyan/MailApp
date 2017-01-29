package com.test.mail.app.business.services.impl;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.managers.UserMusicValidator;
import com.test.mail.app.business.managers.UserValidator;
import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.dao.services.UserDao;
import com.test.mail.app.dao.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    @Transactional
    public Long saveUser(User user) throws BusinessException, DaoException {
        UserValidator.validateUser(user);
        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() throws DaoException {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public void deleteUserByUserName(String userName) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        userDao.deleteUserByUserName(userName);
    }

    @Override
    @Transactional
    public User findByUserName(String userName) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional
    public void addMusic(String userName, UserMusic music) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        UserMusicValidator.validateMusic(music);//TODO implement the method
        userDao.addMusic(userName, music);
    }

    @Override
    @Transactional
    public List<UserMusic> getUserMusics(String userName) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        return userDao.getUserMusics(userName);
    }

    @Override
    @Transactional
    public User loginUser(String userName, String password) throws BusinessException, DaoException {
        UserValidator.validateUserName(userName);
        User foundUser = userDao.findByUserName(userName);
        if (foundUser != null) {
            try {
                if(!SecurityUtils.validatePassword(password, foundUser.getPassword())) {
                    throw new BusinessException("Invalid credentials given");
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
               throw new BusinessException(e);
            }
        } else {
            throw new BusinessException(String.format("The entered '%s' user not found.", userName));
        }
        return foundUser;
    }

    public void changeDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
