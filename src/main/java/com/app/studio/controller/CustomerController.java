package com.app.studio.controller;

import com.app.studio.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author malalanayake
 */
@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService cs) {
        this.customerService = cs;
    }

    @RequestMapping(value = "/view-customers", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        //model.addAttribute("customer", new Customer(new User("")));
        model.addAttribute("listCustomers", this.customerService.listCustomers());
        return "view_customer";
    }

    @RequestMapping("/view-customers/remove/{id}")
    public String removeCustomer(@PathVariable("id") int id) {

        this.customerService.removeCustomer(id);
        return "redirect:/view-customers";
    }

    @RequestMapping("/signup")
    public String editCustomer(Model model) {
        return "signup";
    }
}
