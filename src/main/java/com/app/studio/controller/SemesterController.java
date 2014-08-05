package com.app.studio.controller;

import com.app.studio.service.CustomerService;
import com.app.studio.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dmalalanayake
 */
@Controller
public class SemesterController {

    private SemesterService semesterService;

    @Autowired(required = true)
    @Qualifier(value = "semesterService")
    public void setSemesterService(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @RequestMapping(value = "/semesters", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        //model.addAttribute("customer", new Customer(new User("")));
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "semester";
    }
}
