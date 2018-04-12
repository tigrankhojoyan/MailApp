package com.test.mail.app.web.controller.impl;

import com.test.mail.app.business.exceptions.BusinessException;
import com.test.mail.app.business.services.UserBusinessService;
import com.test.mail.app.business.services.UserRoleService;
import com.test.mail.app.dao.entities.User;
import com.test.mail.app.dao.entities.UserRole;
import com.test.mail.app.dao.exceptions.DaoException;
import com.test.mail.app.web.controller.UserMVCController;
import com.test.mail.app.web.exceptions.MailWebException;
import com.test.mail.app.web.util.FileActions;
import com.test.mail.app.web.util.PropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tigran on 1/7/17.
 */
@Controller
@RequestMapping("/usermvc")
@SessionAttributes("roles")
public class UserMVCControllerImpl implements UserMVCController {

    @Autowired
    @Qualifier("userBusinessService")
    private UserBusinessService userService;

    @Autowired
    PropertiesReader propertiesReader;

    @Autowired
    UserRoleService userRoleService;

    @Override
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String displayLogIn(ModelMap model) {

        if ( SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "redirect:/api/usermvc/user";
        }

        if(model.get("user") == null) {
            User loginUser = new User();
            model.addAttribute("user", loginUser);
        }
        return "login";
    }

    @RequestMapping(value="/user",method= RequestMethod.GET)
    public ModelAndView displayUserPAge(/*ModelMap model*/) {
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws SQLException {
        throw new SQLException("test error");
    }

    @ModelAttribute("roles")
    public List<UserRole> initializeProfiles() {
        return userRoleService.findAll();
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
            result.addError(new ObjectError("common", "Invalid credentials given!"));
        } catch (DaoException e) {
            e.printStackTrace();
            result.addError(new ObjectError("common", "Unknown error. Please try again."));
        }
        return "login";
    }

    @Override
//    @RequestMapping(value="/user",method= RequestMethod.GET)
    public ModelAndView displayUserPage(@ModelAttribute("loggedInUser") User loggedInUser) {
        System.out.println("ppppppppppppppppppppppppdffffffffffffffffffff");
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
            FileActions.createUserFolder(propertiesReader.getUserProperties().getProperty("user.dir.path"), loginUser.getUserName());
            userService.saveUser(loginUser);
            modelMap.addAttribute("user", userService.findByUserName(loginUser.getUserName()));
//            redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
            return "user";
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (MailWebException e) {
            e.printStackTrace();
        }
        return "registration";
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
//            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/api/usermvc/login?logout";
    }

    @PostMapping("/singleFileUpload")
    public String singleFileUpload(@RequestParam("musicFile") MultipartFile file, Model model)
            throws IOException {

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(propertiesReader.getUserProperties().getProperty("user.dir.path"), file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();

            model.addAttribute("msg", "File uploaded successfully.");
        } else {
            model.addAttribute("msg", "Please select a valid file..");
        }

        return "user";
    }


}
