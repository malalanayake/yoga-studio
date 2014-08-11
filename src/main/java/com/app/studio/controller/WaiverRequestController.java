package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import com.app.studio.service.CustomerService;
import com.app.studio.service.WaiverRequestService;
import com.app.studio.service.YogaClassService;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jCalles
 */
@Controller
public class WaiverRequestController {

    private YogaClassService yogaClassService;
    private WaiverRequestService waiverRequestService;
    private CustomerService customerService;

    @Autowired(required = true)
    @Qualifier(value = "yogaClassService")
    public void setYogaClassService(YogaClassService yogaClassService) {
        this.yogaClassService = yogaClassService;
    }

    @Autowired(required = true)
    @Qualifier(value = "waiverRequestService")
    public void setWaiverRequestService(WaiverRequestService waiverRequestService) {
        this.waiverRequestService = waiverRequestService;
    }

    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    /* ************************* Waiver Request ************************* */
    @RequestMapping(value = "/add-waiver-request", method = RequestMethod.GET)
    public String listYogaClasses(Principal user, Model model) {
        getYogaClasses(user.getName(), model);
        return "add-waiver-request";
    }

    @RequestMapping(value = "/add-waiver-request/{id}", method = RequestMethod.GET)
    public String addWaiverRequest(@PathVariable("id") int yogaClassID, Principal user, Model model) throws RequiredDataNotPresent {
        Customer customer = customerService.getCustomerByUsername(user.getName());
        YogaClass yogaClass = yogaClassService.getYogaClassByID(yogaClassID);

        this.waiverRequestService.createNewWaiverRequest(yogaClass, customer);
        model.addAttribute("msg", "Waiver Request was created and sent successfully");

        getYogaClasses(user.getName(), model);
        return "add-waiver-request";
    }

    public void getYogaClasses(String username, Model model) {
        // Get existing waivers
        Set<WaiverRequest> waiverRequests = this.customerService.getCustomerByUsername(username).getSetOfWaiverRequests();
        // Get all yoga classes
        List<YogaClass> classes = this.yogaClassService.listOfYogaClasses();
        for (WaiverRequest request : waiverRequests) {
            classes.remove(request.getYogaClass());
        }
        model.addAttribute("listWaivers", waiverRequests);
        model.addAttribute("listYogaClasses", classes);
    }
}
