package com.test.mail.app.dao.services;

import com.test.mail.app.dao.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tigran on 11/6/16.
 */
public class AbstractDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() throws DaoException {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }

    }

}
