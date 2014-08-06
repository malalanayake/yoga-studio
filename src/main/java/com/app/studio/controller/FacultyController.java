package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.WaiverRequest;
import com.app.studio.service.FacultyService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Yen
 */
@Controller
public class FacultyController {

    private FacultyService facultyService;

    @Autowired(required = true)
    @Qualifier(value = "facultyService")
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/waivers", method = RequestMethod.GET)
    public String listWaivers(Principal user, Model model) {
        getWaivers(user.getName(), model);
        return "waiver";
    }

    @RequestMapping(value = "/waivers/approve/{id}", method = RequestMethod.GET)
    public String approveWaiver(@PathVariable("id") int id, Principal user, Model model) {
        submitWaiverResponse(id, user.getName(), model, true);
        return "waiver";
    }

    @RequestMapping(value = "/waivers/reject/{id}", method = RequestMethod.GET)
    public String rejectWaiver(@PathVariable("id") int id, Principal user, Model model) {
        submitWaiverResponse(id, user.getName(), model, false);
        return "waiver";
    }

    private void getWaivers(String username, Model model) {
        try {
            model.addAttribute("listWaivers", this.facultyService.getFacultyByUsername(username).getSetOfWaiverRequests());
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
    }

    private void submitWaiverResponse(int waiverID, String username, Model model, boolean isApproved) {
        try {
            WaiverRequest waiver = this.facultyService.submitWaiverResponse(waiverID, isApproved);
            model.addAttribute("msg", buildWaiverResponseMessage(waiver, isApproved));
            getWaivers(username, model);
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
    }

    private String buildWaiverResponseMessage(WaiverRequest waiver, boolean isApproved) {
        StringBuffer message = new StringBuffer();
        message.append("Waiver request for ").append(waiver.getYogaClass().getName())
                .append(" submitted by ").append(waiver.getCustomer().getUser().getFirstName())
                .append(" ").append(waiver.getCustomer().getUser().getLastName());
        if (isApproved) {
            message.append(" has been approved.");
        } else {
            message.append(" has been rejected.");
        }
        System.out.println("\n###############\n" + message.toString() + "\n###############\n");
        return message.toString();
    }
}
