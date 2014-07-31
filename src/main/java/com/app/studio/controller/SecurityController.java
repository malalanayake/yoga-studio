package com.app.studio.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
