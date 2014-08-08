
package com.app.studio.controller;

import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.service.YogaSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author malalanayake
 */
@Controller
public class SectionController {
    private YogaSectionService yogaSectionService;

    @Autowired(required = true)
    @Qualifier(value = "yogaSectionService")
    public void setYogaSectionService(YogaSectionService yogaSectionService) {
        this.yogaSectionService = yogaSectionService;
    }
    
    @RequestMapping(value = "/sections", method = RequestMethod.GET)
    public String listSemesters(Model model) {
        model.addAttribute("section", new Section());
        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        return "section";
    }
    
    
}
