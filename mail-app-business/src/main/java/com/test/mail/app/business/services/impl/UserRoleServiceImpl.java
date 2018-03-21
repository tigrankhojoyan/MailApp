package com.test.mail.app.business.services.impl;

import com.test.mail.app.business.services.UserRoleService;
import com.test.mail.app.dao.entities.UserRole;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.dao.services.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tigrank
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public List<UserRole> findAll() {
        try {
            return userRoleDao.findAll();
        } catch (DaoException e) {
            return null;
        }
    }

    @Override
    public UserRole findByType(String type) throws DaoException {
        return userRoleDao.findByType(type);
    }

    @Override
    public UserRole findById(int id) throws DaoException {
        return userRoleDao.findById(id);
    }
}
