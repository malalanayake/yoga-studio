package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.User;
import com.app.studio.service.CustomerService;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping("/signup/add")
    public String addCustomer(@ModelAttribute("user") User u, Model model) {
        Customer customer = new Customer(u);
        try {
            customerService.addCustomer(customer);
            model.addAttribute("msg", "Account succesfully created");

        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (RecordAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "signup";
    }

    // DEVELOPMENT
    /* private void getYogaClasses(String username, Model model) {
     try {
     model.addAttribute("listWaivers", this.YogaClassService.getFacultyByUsername(username).getSetOfWaiverRequests());
     } catch (RequiredDataNotPresent ex) {
     model.addAttribute("error", ex.getMessage());
     }
     }*/
    @RequestMapping(value = "/request-waive-prerequisites", method = RequestMethod.GET)
    public String addWaiverRequest(Model model) {
        return "waiverrequest";
    }

}
