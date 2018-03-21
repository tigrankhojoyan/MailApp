package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.entities.UserRole;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.dao.services.AbstractDao;
import com.test.mail.app.dao.services.UserRoleDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author tigrank
 */

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao implements UserRoleDao {

    @Override
    @Transactional
    public List<UserRole> findAll() throws DaoException{
        try {
            Criteria crit = getSession().createCriteria(UserRole.class);
            crit.addOrder(Order.asc("role"));
            return (List<UserRole>)crit.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional
    public UserRole findByType(String type) throws DaoException {
        Criteria crit = getSession().createCriteria(UserRole.class);
        crit.add(Restrictions.eq("role", type));
        return (UserRole) crit.uniqueResult();
    }

    @Override
    @Transactional
    public UserRole findById(int id) throws DaoException {
        return (UserRole) getSession().get(UserRole.class, id);
    }
}
