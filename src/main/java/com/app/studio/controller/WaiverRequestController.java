
package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import com.app.studio.service.WaiverRequestService;
import com.app.studio.service.YogaClassService;
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
public class WaiverRequestController {
    
    private YogaClassService yogaClassService;
    private WaiverRequestService waiverRequestService;

  
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
        public String listYogaClasses (Model model) {
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "add-waiver-request";
    }
    
//    @RequestMapping(value = "/add-waiver-request", method = RequestMethod.POST)
//    public String addWaiverRequest(@ModelAttribute("waiverRequest") YogaClass yogaclass, Customer customer, Model model) throws RequiredDataNotPresent {
//        String error = "";
//       
//            
//                this.waiverRequestService.createNewWaiverRequest(yogaclass, customer);
//                model.addAttribute("msg", "Waiver Request was created and sent successfully");
//     
//
//        model.addAttribute("waiverRequest", new WaiverRequest());
//        return "waiverRequest";


//}
    
}
