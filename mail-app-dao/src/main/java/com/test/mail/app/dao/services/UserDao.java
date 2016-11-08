package com.test.mail.app.dao.services;

import com.test.mail.app.dao.entities.User;

import java.util.List;

/**
 * Created by tigran on 11/6/16.
 */
public interface UserDao {
    void saveUser(User user);

    List<User> findAllUsers();

    void deleteUserByUserName(String userName);

    User findByUserName(String userName);

}
