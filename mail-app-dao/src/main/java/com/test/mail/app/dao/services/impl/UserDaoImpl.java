package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.services.AbstractDao;
import com.test.mail.app.dao.services.UserDao;
import org.hibernate.Criteria;
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
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = getSession().createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    @Override
    public void deleteUserByUserName(String userName) {
        Query query = getSession().createSQLQuery("delete from Users where USER_NAME = :userName");
        query.setString("userName", userName);
        query.executeUpdate();
    }

    @Override
    public User findByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        return (User) criteria.uniqueResult();
    }
}
