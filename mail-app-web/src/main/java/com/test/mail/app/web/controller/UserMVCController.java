package com.test.mail.app.web.controller;

import com.test.mail.app.dao.entities.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by tigran on 1/7/17.
 */
public interface UserMVCController {

    ModelAndView displayLogIn();

    ModelAndView executeLogeIn(@ModelAttribute("loginUser")User loginUser, RedirectAttributes redir);

    ModelAndView displayUserPage(@ModelAttribute("loggedInUser")User loggedInUser );

}
