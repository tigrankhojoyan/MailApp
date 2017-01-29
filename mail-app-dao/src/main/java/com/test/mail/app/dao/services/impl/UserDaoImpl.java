package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserMusic;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.dao.services.AbstractDao;
import com.test.mail.app.dao.services.UserDao;
import com.test.mail.app.dao.utils.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tigran on 11/6/16.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public Long saveUser(User user) throws DaoException {
        return (Long)getSession().save(user);
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        Criteria criteria = getSession().createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    @Override
    public void deleteUserByUserName(String userName) throws DaoException {
        Query query = getSession().createSQLQuery("delete from Users where USER_NAME = :userName");
        query.setString("userName", userName);
        query.executeUpdate();
    }

    @Override
    public User findByUserName(String userName) throws DaoException {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    public void addMusic(String userName, UserMusic music) throws DaoException {
        User user = findByUserName(userName);
        user.addMusic(music);
        getSession().update(user);
    }

    @Override
    public List<UserMusic> getUserMusics(String userName) throws DaoException {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        Hibernate.initialize(UserMusic.class);
        User user = (User) criteria.uniqueResult();
        return user.getUserMusics();
    }

    @Override
    public User updateUserPassword(String userName, String oldPassword, String newPassword) throws DaoException {
        User user = findByUserName(userName);
        user.setPassword(newPassword);
        getSession().update(user);
        return user;
    }
}
