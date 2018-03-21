package com.test.mail.app.dao.services;

import com.test.mail.app.dao.entities.UserRole;
import com.test.mail.app.dao.exceptions.DaoException;

import java.util.List;

/**
 * @author tigrank
 */
public interface UserRoleDao {

    List<UserRole> findAll() throws DaoException;

    UserRole findByType(String type) throws DaoException;

    UserRole findById(int id) throws DaoException;
}
