package com.app.studio.controller;

import com.app.studio.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String listWaivers(Model model) {
        //model.addAttribute("customer", new Customer(new User("")));
//        model.addAttribute("listSemetsters", this.facultyService.getFacultyByID(id).getSetOfWaiverRequests()));
        return "waiver";
    }
}
