package com.test.mail.app.web.controller.impl;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.web.controller.UserMVCController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

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
    public String displayLogIn(ModelMap model) {
        if(model.get("user") == null) {
            User loginUser = new User();
            model.addAttribute("user", loginUser);
        }
        return "login";
    }

    @Override
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String executeLogeIn(@Valid User loginUser, /*RedirectAttributes redirectAttributes,*/ BindingResult result, ModelMap modelMap) {
//        modelMap.addAttribute("user", loginUser);
        if (result.hasErrors()) {
            return "login";
        }

        try {
            User loggedInUser = userService.loginUser(loginUser.getUserName(), loginUser.getPassword());
            modelMap.addAttribute("user", loggedInUser);
//            redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
            return "user";
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return "login";
    }

    @Override
    @RequestMapping(value="/user",method= RequestMethod.GET)
    public ModelAndView displayUserPage(@ModelAttribute("loggedInUser") User loggedInUser) {
        if(loggedInUser == null || loggedInUser.getUserName() == null || loggedInUser.getPassword() == null) {
            return new ModelAndView(new RedirectView("login"));
        }
        return new  ModelAndView("user", "loggedInUser", loggedInUser);
    }

    @Override
    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String displayRegistration(ModelMap model) {
        if(model.get("user") == null) {
            User loginUser = new User();
            model.addAttribute("user", loginUser);
        }
        return "registration";
    }

    @Override
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String executeRegistration(@Valid User loginUser, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            Long registrationUserId = userService.saveUser(loginUser);
            modelMap.addAttribute("user", userService.findByUserName(loginUser.getUserName()));
//            redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
            return "user";
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return "registration";
    }


}
