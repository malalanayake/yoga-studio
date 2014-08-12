package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.EnrolledSection;
import com.app.studio.model.Section;
import com.app.studio.model.User;
import com.app.studio.service.CustomerService;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "login";
    }

    @RequestMapping(value = "/request-waive-prerequisites", method = RequestMethod.GET)
    public String addWaiverRequest(Model model) {
        return "waiverrequest";
    }

    @RequestMapping(value = "/enroll", method = RequestMethod.GET)
    public String listEnrollmentSections(Principal user, Model model) {
        getOpenEnrollments(user.getName(), model);
        return "enroll";
    }

    @RequestMapping(value = "/enroll/{id}", method = RequestMethod.GET)
    public String enroll(@PathVariable("id") int id, Principal user, Model model) {
        try {
            Section section = this.customerService.getSectionById(id);
            boolean isEnrolled = this.customerService.enroll(user.getName(), section);
            if (isEnrolled) {
                model.addAttribute("msg", "You have been successfully enrolled in "
                        + section.getYogaClass().getName() + " - Section " + section.getId()
                        + " for Semester " + section.getSemester().getStartdate()
                        + " - " + section.getSemester().getEnddate());
                getOpenEnrollments(user.getName(), model);
                return "enroll";
            } else {
                model.addAttribute("section", section);
                return "waitlist";
            }
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            getOpenEnrollments(user.getName(), model);
            return "enroll";
        }

    }

    @RequestMapping(value = "/waitlist", method = RequestMethod.POST)
    public String waitlist(@RequestParam String waitlist, @ModelAttribute("section") Section s, Principal user, Model model) {
        System.out.println("############### RequestParam = " + waitlist + ", Section ID: " + s.getId());
        if ("Yes".equals(waitlist)) {
            try {
                Section section = this.customerService.waitlist(user.getName(), s.getId());
                model.addAttribute("msg", "You have been successfully waitlisted in "
                        + section.getYogaClass().getName() + " - Section " + section.getId()
                        + " for Semester " + section.getSemester().getStartdate()
                        + " - " + section.getSemester().getEnddate());
            } catch (RequiredDataNotPresent ex) {
                model.addAttribute("error", ex.getMessage());
            }
            getOpenEnrollments(user.getName(), model);
            return "enroll";
        } else {
            return "redirect:/enroll";
        }
    }

    @RequestMapping(value = "/enrolled-sections", method = RequestMethod.GET)
    public String listEnrolledSections(Principal user, Model model) {
        model.addAttribute("enrolledSections", this.customerService.getCustomerByUsername(user.getName()).getSetOfEnrolledSections());
        return "enrolled-section";
    }

    @RequestMapping(value = "/drop-section", method = RequestMethod.GET)
    public String listDropSections(Principal user, Model model) {
        model.addAttribute("enrolledSections", this.customerService.getCustomerByUsername(user.getName()).getSetOfEnrolledSections());
        return "drop-section";
    }

    @RequestMapping(value = "/drop-section/{id}", method = RequestMethod.GET)
    public String dropSection(@PathVariable("id") int id, Principal user, Model model) {
        try {
            Section section = this.customerService.drop(id);
            model.addAttribute("msg", "You have successfully dropped "
                    + section.getYogaClass().getName() + " - Section " + section.getId()
                    + " for Semester " + section.getSemester().getStartdate()
                    + " - " + section.getSemester().getEnddate());
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("enrolledSections", this.customerService.getCustomerByUsername(user.getName()).getSetOfEnrolledSections());
        return "drop-section";
    }

    private void getOpenEnrollments(String username, Model model) {
        // Get enrolled sections
        Set<EnrolledSection> enrolledSections = this.customerService.getCustomerByUsername(username).getSetOfEnrolledSections();
        // Get sections open for sign up
        List<Section> availableSections = this.customerService.listAvailableSections();
        for (EnrolledSection enrolled : enrolledSections) {
            // If section is not in available sections, 
            // the class has already expired and doesn't need to be displayed
            if (!availableSections.remove(enrolled.getSection())) {
                enrolledSections.remove(enrolled);
            }
        }
        model.addAttribute("enrolledSections", enrolledSections);
        model.addAttribute("availableSections", availableSections);
    }
}
