package com.test.mail.app.web.controller.impl;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.web.controller.UserMVCController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by tigran on 1/7/17.
 */
@Controller
@RequestMapping("/usermvc")
public class UserMVCControllerImpl implements UserMVCController {

    @Autowired
    @Qualifier("userBusinessService")
    private UserBusinessService userService;

    @Override
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public ModelAndView displayLogIn() {
        User loginUser = new User();
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginUser", loginUser);
        return modelAndView;
    }

    @Override
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public ModelAndView executeLogeIn(@ModelAttribute("loginUser") User loginUser, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        try {
            User loggedInUser = userService.loginUser(loginUser.getUserName(), loginUser.getPassword());
            modelAndView = new ModelAndView(new RedirectView("user"));
            redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
//            modelAndView.addObject("loggedInUser", loggedInUser);
            return modelAndView;
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new ModelAndView("login");
    }

    @Override
    @RequestMapping(value="/user",method= RequestMethod.GET)
    public ModelAndView displayUserPage(@ModelAttribute("loggedInUser") User loggedInUser) {
        if(loggedInUser == null || loggedInUser.getUserName() == null || loggedInUser.getPassword() == null) {
            return new ModelAndView(new RedirectView("login"));
        }
        return new  ModelAndView("user", "loggedInUser", loggedInUser);
    }
}
