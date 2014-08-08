package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Semester;
import com.app.studio.model.YogaClass;
import com.app.studio.service.YogaClassService;
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
public class YogaClassController {

    private YogaClassService yogaClassService;

    @Autowired(required = true)
    @Qualifier(value = "yogaClassService")
    public void setYogaClassService(YogaClassService yogaClassService) {
        this.yogaClassService = yogaClassService;
    }

    @RequestMapping(value = "/yogaclasses", method = RequestMethod.GET)
    public String listYogaClasses(Model model) {
        model.addAttribute("yogaClass", new YogaClass());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

    @RequestMapping(value = "/yogaclasses/add", method = RequestMethod.POST)
    public String listYogaClasses(@ModelAttribute("yogaClass") YogaClass c, Model model) {
        String error = "";
        try {
            if (c.getId() == 0) {
                //Check whether the pre requesits is there or not
//                if (y > 0) {
//                    YogaClass yogaPre = yogaClassService.getYogaClassByID(y);
//                    c.addPrerequisite(yogaPre);
//                }
                this.yogaClassService.createYogaClass(c);
                model.addAttribute("msg", "Yoga Class is successfully created");
            } else {
                this.yogaClassService.updateYogaClass(c);
                model.addAttribute("msg", "Yoga Class is successfully updated");
            }
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (RecordAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
        }

        model.addAttribute("yogaClass", new YogaClass());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

    @RequestMapping(value = "/yogaclasses/add/pre/{yoga}/{pre}", method = RequestMethod.GET)
    public String addPreReqYogaClass(@PathVariable("yoga") int yoga, @PathVariable("pre") int pre, Model model) {
        YogaClass yogaClass = new YogaClass();
        try {
            yogaClass = yogaClassService.getYogaClassByID(yoga);
            YogaClass yogaPre = yogaClassService.getYogaClassByID(pre);
            yogaClass.addPrerequisite(yogaPre);

        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("yogaClass", yogaClass);
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

    @RequestMapping(value = "/yogaclasses/edit/{id}", method = RequestMethod.GET)
    public String editSemesters(@PathVariable("id") int id, Model model) {
        YogaClass yogaClass = new YogaClass();
        try {
            yogaClass = yogaClassService.getYogaClassByID(id);
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("yogaClass", yogaClass);
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

}
