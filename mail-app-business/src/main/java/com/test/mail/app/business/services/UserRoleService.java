package com.test.mail.app.business.services;

import com.test.mail.app.dao.entities.UserRole;
import com.test.mail.app.dao.exceptions.DaoException;

import java.util.List;

/**
 * @author tigrank
 */
public interface UserRoleService {


    List<UserRole> findAll();

    UserRole findByType(String type) throws DaoException;

    UserRole findById(int id) throws DaoException;
}
