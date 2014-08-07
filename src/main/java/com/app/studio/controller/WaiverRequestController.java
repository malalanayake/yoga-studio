package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import com.app.studio.service.CustomerService;
import com.app.studio.service.WaiverRequestService;
import com.app.studio.service.YogaClassService;
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

    /* ************************* Waiver Request ************************* */
    @RequestMapping(value = "/add-waiver-request", method = RequestMethod.GET)
    public String listYogaClasses(Model model) {
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "add-waiver-request";
    }

    @RequestMapping(value = "/add-waiver-request/{id}/{name}", method = RequestMethod.POST)
    public String addWaiverRequest(@PathVariable("id") int yogaClassID,
           @PathVariable("name") String customerName, Model model) throws RequiredDataNotPresent {
        
        Customer customer = customerService.getCustomerByUsername(customerName);
        YogaClass yogaClass = yogaClassService.getYogaClassByID(yogaClassID);
        
        this.waiverRequestService.createNewWaiverRequest(yogaClass, customer);
        model.addAttribute("msg", "Waiver Request was created and sent successfully");

        //model.addAttribute("waiverRequests", customer.getSetOfWaiverRequests());
        return "waiverRequest";

    }

}
