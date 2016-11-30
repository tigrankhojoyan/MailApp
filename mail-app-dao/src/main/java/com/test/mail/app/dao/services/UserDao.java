package com.test.mail.app.dao.services;

import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.exceptions.DaoException;

import java.util.List;

/**
 * Created by tigran on 11/6/16.
 */
public interface UserDao {
    Long saveUser(User user) throws DaoException;

    List<User> findAllUsers() throws DaoException;

    void deleteUserByUserName(String userName) throws DaoException;

    User findByUserName(String userName) throws DaoException;

    void addMusic(String userName, UserMusic music) throws DaoException;

    List<UserMusic> getUserMusics(String userName) throws DaoException;

    User updateUserPassword(String userName, String oldPassword, String newPassword) throws DaoException;

}
