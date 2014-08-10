package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Semester;
import com.app.studio.service.SemesterService;
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
    public String requestToListOfSemesters(Model model) {
        model.addAttribute("semester", new Semester());
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "semester";
    }

    @RequestMapping(value = "/semesters/edit/{id}", method = RequestMethod.GET)
    public String requestToUpdateSemester(@PathVariable("id") int id, Model model) {
        Semester sem = new Semester();
        sem = semesterService.getSemeterByID(id);
        model.addAttribute("semester", sem);
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "semester";
    }

    @RequestMapping(value = "/semesters/remove/{id}", method = RequestMethod.GET)
    public String requestToRemoveSemester(@PathVariable("id") int id, Model model) {
        Semester sem = semesterService.getSemeterByID(id);
        try {
            semesterService.deleteSemester(sem);
            model.addAttribute("msg", "Semester is successfully deleted");
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.toString());
        }
        model.addAttribute("semester", new Semester());
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "redirect:/semesters";
    }

    @RequestMapping(value = "/semesters/add", method = RequestMethod.POST)
    public String requestToCreateSemester(@ModelAttribute("semester") Semester c, Model model) {
        String error = "";
        try {
            if (c.getId() == 0) {
                this.semesterService.createNewSemester(c);
                model.addAttribute("msg", "Semester is successfully created");
            } else {
                this.semesterService.updateSemeter(c);
                model.addAttribute("msg", "Semester is successfully updated");
            }
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (RecordAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
        }

        model.addAttribute("semester", new Semester());
        model.addAttribute("listSemetsters", this.semesterService.listOfAllSemesters());
        return "semester";

    }
}
