package com.test.mail.app.business.services;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.exceptions.DaoException;

import java.util.List;

/**
 * Created by tigran on 12/1/16.
 */
public interface UserBusinessService {
    Long saveUser(User user) throws BusinessException, DaoException;

    List<User> findAllUsers() throws DaoException;

    void deleteUserByUserName(String userName) throws BusinessException, DaoException;

    User findByUserName(String userName) throws BusinessException, DaoException;

    void addMusic(String userName, UserMusic music) throws BusinessException, DaoException;

    List<UserMusic> getUserMusics(String userName) throws BusinessException, DaoException;
}
