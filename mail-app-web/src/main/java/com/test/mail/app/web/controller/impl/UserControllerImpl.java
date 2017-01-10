package com.test.mail.app.web.controller.impl;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.web.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tigran on 12/18/16.
 */
@Controller
@EnableTransactionManagement
@RequestMapping("/user")
//TODO
public class UserControllerImpl implements UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userBusinessService")
    private UserBusinessService businessService;

    @Override
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            LOGGER.info("Creating the following user account: {}", user);
            Long userID = businessService.saveUser(user);
            return new ResponseEntity<Object>(userID, HttpStatus.ACCEPTED);
        } catch (DaoException e) {
            String errorMessage = String.format("Failed to persist the '%s' user data to db", user.toString());
            LOGGER.error(errorMessage, e);
            return new ResponseEntity<Object>(errorMessage, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (BusinessException e) {
            String errorMessage = String.format("Invalid user data: '%s' ", user.toString());
            LOGGER.error(errorMessage, e);
            return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> testUrl() {
        return new ResponseEntity<String>("Test url", HttpStatus.OK);
    }
}
