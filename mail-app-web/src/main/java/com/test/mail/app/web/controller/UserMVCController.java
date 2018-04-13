package com.test.mail.app.web.controller;

import com.test.mail.app.dao.entities.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by tigran on 1/7/17.
 */
public interface UserMVCController {

    String displayLogIn(ModelMap model);

    String executeLogeIn(@Valid User loginUser,/* RedirectAttributes redir, */BindingResult result, ModelMap modelMap);

    ModelAndView displayUserPage(@ModelAttribute("loggedInUser")User loggedInUser );

    String displayRegistration(ModelMap model);

    String executeRegistration(@Valid User loginUser, BindingResult result, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response);

}
