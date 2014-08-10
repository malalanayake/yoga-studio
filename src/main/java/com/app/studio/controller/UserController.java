package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.User;
import com.app.studio.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jCalles
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/manage-profile", method = RequestMethod.GET)
    public String showCurrentProfile(Model model, Principal user) {
        model.addAttribute("user", this.userService.getUserByUserName(user.getName()));
        return "manage-profile";

    }

    @RequestMapping(value = "/manage-profile/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User u, Model model) {
        try {
            User user = userService.getUserByUserName(u.getUsername());
            user.setPassword(u.getPassword());
            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setSequrityQuestion(u.getSequrityQuestion());
            user.setAnswer(u.getAnswer());
            user = this.userService.updateUser(user);
            model.addAttribute("msg", "The profile was succesfully updated");
            model.addAttribute("user", user);
            return "manage-profile";
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", u);
            return "manage-profile";
        }

    }
}
