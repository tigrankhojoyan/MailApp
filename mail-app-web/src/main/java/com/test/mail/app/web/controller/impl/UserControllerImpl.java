package com.test.mail.app.web.controller.impl;

import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.web.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tigran on 12/18/16.
 */
@Controller
@RequestMapping("/user")
//TODO
public class UserControllerImpl implements UserController {

    @Autowired
    @Qualifier("userBusinessService")
    private UserBusinessService businessService;

    @Override
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
}
