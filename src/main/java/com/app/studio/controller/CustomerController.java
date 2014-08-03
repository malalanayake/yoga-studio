package com.app.studio.controller;

import com.app.studio.model.Customer;
import com.app.studio.model.User;
import com.app.studio.security.Roles;
import com.app.studio.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Mediate the customer operations
 * 
 * @author malalanayake
 * 
 */
@Controller
public class CustomerController {

	private CustomerService customerService;

	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setPersonService(CustomerService cs) {
		this.customerService = cs;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String listCustomers(Model model) {
		//model.addAttribute("customer", new Customer(new User("")));
		model.addAttribute("listCustomers", this.customerService.listCustomers());
		return "customer";
	}

	/**
	 * For add and update customer both
	 * 
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/customer/add", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customer") Customer c) {

		if (c.getId() == 0) {
			this.customerService.addCustomer(c);
		} else {
			this.customerService.updateCustomer(c);
		}

		return "redirect:/customers";

	}

	@RequestMapping("/customer/remove/{id}")
	public String removeCustomer(@PathVariable("id") int id) {

		this.customerService.removeCustomer(id);
		return "redirect:/customers";
	}

	@RequestMapping("/customer/edit/{id}")
	public String editCustomer(@PathVariable("id") int id, Model model) {
		model.addAttribute("customer", this.customerService.getCustomerById(id));
		model.addAttribute("listCustomers", this.customerService.listCustomers());
		return "customer";
	}

}
