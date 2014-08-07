package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Semester;
import com.app.studio.model.YogaClass;
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

}
