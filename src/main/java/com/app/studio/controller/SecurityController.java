package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Mediate the all custom security request
 *
 * @author malalanayake
 *
 */
@Controller
public class SecurityController {

    UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String listCustomers(Principal user, Model model) {
        if (user != null) {
            model.addAttribute("message", "Hi " + user.getName()
                    + ", You dont have proper permission to access this content");
        } else {
            model.addAttribute("You dont have proper permission to access this content");
        }
        return "access-denied";
    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String listCustomersw(Model model) {
        return "main";
    }

    // FORGOT PASSWORD
    //ASK USERNAME
    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public String askUserName(Model model) {
        model.addAttribute("user", new User());
        return "forgot-password";
    }

    //CHECK USER VALUES
    @RequestMapping(value = "/forgot-password/answer", method = RequestMethod.POST)
    public String checkUserValues(@ModelAttribute("user") User u, Model model) {
        if (!u.getUsername().equals("")) {
            if (u.getSequrityQuestion()==null) {
                User user = userService.getUserByUserName(u.getUsername());
                if (user == null) {
                    model.addAttribute("user", new User());
                    model.addAttribute("error", "Wrong user, please try again");
                } else {
                    user.setAnswer("");
                    model.addAttribute("msg", "User found, please answer the security question");
                    model.addAttribute("user", user);
                }
                return "forgot-password";

            } else {

                User user = userService.getUserByUserName(u.getUsername());

                if (u.getAnswer().equals(user.getAnswer())) {
                    model.addAttribute("user", user);
                    model.addAttribute("msg", "Correct answer, now you can change your password");
                    return "reset-password";

                } else {
                    user.setAnswer("");
                    model.addAttribute("user", user);
                    model.addAttribute("error", "Wrong answer, please try again");
                }
            }

        }

        return "forgot-password";
    }
    
    //ASK FOR PASSWORD
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String askPassword(Model model, User u) throws RequiredDataNotPresent {
        
        User user = userService.getUserByUserName(u.getUsername());
        user.setPassword(u.getPassword());
        userService.updateUser(user);
        model.addAttribute("msg", "Password changed succesfully, now you can login with your new Password");
 
        return "/login";
    }
    
    
    
    
    

}
