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
 * Controller class for manage the yoga classes
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

    /**
     * Load the yoga classes into view
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/yogaclasses", method = RequestMethod.GET)
    public String requestListOfYogaClasses(Model model) {
        model.addAttribute("yogaClass", new YogaClass());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

    /**
     * Add or Update Yoga class
     *
     * @param c
     * @param model
     * @return
     */
    @RequestMapping(value = "/yogaclasses/add", method = RequestMethod.POST)
    public String requestToCreateYogaClass(@ModelAttribute("yogaClass") YogaClass c, Model model) {
        String error = "";
        try {
            if (c.getId() == 0) {
                this.yogaClassService.createYogaClass(c);
                model.addAttribute("msg", "Yoga Class " + c.getName() + "is successfully created");
            } else {
                YogaClass yoga = yogaClassService.getYogaClassByID(c.getId());
                yoga.setPrice(c.getPrice());
                this.yogaClassService.updateYogaClass(yoga);
                model.addAttribute("msg", "Yoga Class " + c.getName() + " is successfully updated");
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

    /**
     * Add new pre requisites on Yoga class
     *
     * @param yoga
     * @param pre
     * @param model
     * @return
     */
    @RequestMapping(value = "/yogaclasses/add/pre", method = RequestMethod.POST)
    public String addPreReqYogaClass(@ModelAttribute("id") int yoga, @ModelAttribute("preRequesits") int pre, Model model) {
        YogaClass yogaClass = new YogaClass();
        try {
            yogaClass = yogaClassService.getYogaClassByID(yoga);
            YogaClass yogaPre = yogaClassService.getYogaClassByID(pre);
            yogaClass.addPrerequisite(yogaPre);
            yogaClass = yogaClassService.updateYogaClass(yogaClass);
            model.addAttribute("msg", "Yoga Class " + yogaPre.getName() + " is added as prerequisites");

        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("yogaClass", yogaClass);
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

    /**
     * Load the yoga class in edit mode
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/yogaclasses/edit/{id}", method = RequestMethod.GET)
    public String requestToUpdateYogaClass(@PathVariable("id") int id, Model model) {
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

    /**
     * Remove the pre requisites form given yoga class
     *
     * @param yoga
     * @param pre
     * @param model
     * @return
     */
    @RequestMapping(value = "/yogaclasses/remove/pre/{yoga}/{pre}", method = RequestMethod.GET)
    public String removePreRequesits(@PathVariable("yoga") int yoga, @PathVariable("pre") int pre, Model model) {
        YogaClass yogaClass = new YogaClass();
        try {
            YogaClass yogaClassToBeModified = yogaClassService.getYogaClassByID(yoga);
            YogaClass preReqRemove = yogaClassService.getYogaClassByID(pre);
            yogaClass = yogaClassService.removePreReqYogaClass(yogaClassToBeModified, preReqRemove);
            model.addAttribute("msg", "Yoga Class " + preReqRemove.getName() + " is removed from prerequisites");
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("yogaClass", yogaClass);
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

    /**
     * Remove yoga class
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/yogaclasses/remove/{id}", method = RequestMethod.GET)
    public String requestToDeleteYogaClass(@PathVariable("id") int id, Model model) {
        YogaClass yogaClass = new YogaClass();
        try {
            yogaClass = yogaClassService.getYogaClassByID(id);
            yogaClassService.deleteYogaClass(yogaClass);
            model.addAttribute("msg", "Yoga Class " + yogaClass.getName() + " is deleted");

        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("yogaClass", new YogaClass());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        return "yogaclass";
    }

}
