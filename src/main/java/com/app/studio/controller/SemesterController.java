package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.Semester;
import com.app.studio.service.CustomerService;
import com.app.studio.service.SemesterService;
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
    public String listSemesters(Model model) {
        model.addAttribute("semester", new Semester());
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "semester";
    }

    @RequestMapping(value = "/semesters/edit/{id}", method = RequestMethod.GET)
    public String editSemesters(@PathVariable("id") int id, Model model) {
        Semester sem  = new Semester();
        sem = semesterService.getSemeterByID(id);
        model.addAttribute("semester", sem);
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "semester";
    }

    @RequestMapping(value = "/semesters/remove/{id}", method = RequestMethod.GET)
    public String removeSemesters(@PathVariable("id") int id, Model model) {
        Semester sem = semesterService.getSemeterByID(id);
        try {
            semesterService.deleteSemester(sem);
        } catch (RequiredDataNotPresent ex) {
           
        }
        model.addAttribute("semester", new Semester());
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "redirect:/semesters";
    }

    @RequestMapping(value = "/semesters/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("semester") Semester c) {
        String error = "";
        try {
            if (c.getId() == 0) {

                this.semesterService.createNewSemester(c);

            } else {
                this.semesterService.updateSemeter(c);
            }
        } catch (RequiredDataNotPresent ex) {
            error = ex.toString();
        } catch (RecordAlreadyExistException ex) {
            error = ex.toString();
        }

        return "redirect:/semesters";

    }
}
