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
import org.springframework.web.bind.annotation.PathVariable;
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

    // Manage Profile 
    //Show Current Profile
    @RequestMapping(value = "/manage-profile", method = RequestMethod.GET)
    public String showCurrentProfile(Model model, Principal user) {
        model.addAttribute("user", this.userService.getUserByUserName(user.getName()));
        return "manage-profile";

    }

    //Update Profile
    @RequestMapping(value = "/manage-profile/{name}/{password}/{firstName}/{lastName}/{securityQuestion}/{answer}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("name") String customerName, @PathVariable("password") String password,
            @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("sequrityQuestion") String sequrityQuestion,
            @PathVariable("answer") String answer, Model model) throws RequiredDataNotPresent {

        User user = userService.getUserByUserName(customerName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAnswer(answer);
        user.setSequrityQuestion(sequrityQuestion);

        user = this.userService.updateUser(user);
        model.addAttribute("msg", "The profile was succesfully updated");
        model.addAttribute("user", user);
        return "manage-profile";

    }
}
