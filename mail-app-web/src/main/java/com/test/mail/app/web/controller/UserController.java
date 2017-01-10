package com.test.mail.app.web.controller;

import com.test.mail.app.dao.entities.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tigran on 12/18/16.
 */
public interface UserController {

    @RequestMapping(value = "createuser", method = RequestMethod.POST,
            headers = {"Content-type=application/json"}, produces = "application/json; charset=UTF-8")
    ResponseEntity<Object> createUser(@RequestBody User user/*, @RequestHeader HttpHeaders authorizationHeader*/);

    @RequestMapping(value = "test")
    ResponseEntity<String> testUrl();
}
