package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.YogaClass;
import com.app.studio.service.FacultyService;
import com.app.studio.service.SemesterService;
import com.app.studio.service.YogaClassService;
import com.app.studio.service.YogaSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author malalanayake
 */
@Controller
public class SectionController {

    private YogaSectionService yogaSectionService;
    private YogaClassService yogaClassService;
    private FacultyService facultyService;
    private SemesterService semesterService;

    @Autowired(required = true)
    @Qualifier(value = "yogaSectionService")
    public void setYogaSectionService(YogaSectionService yogaSectionService) {
        this.yogaSectionService = yogaSectionService;
    }

    @Autowired(required = true)
    @Qualifier(value = "yogaClassService")
    public void setYogaClassService(YogaClassService yogaClassService) {
        this.yogaClassService = yogaClassService;
    }

    @Autowired(required = true)
    @Qualifier(value = "facultyService")
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Autowired(required = true)
    @Qualifier(value = "semesterService")
    public void setSemesterService(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @RequestMapping(value = "/sections", method = RequestMethod.GET)
    public String listSections(Model model) {
        model.addAttribute("section", new Section());
        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        model.addAttribute("listSemesters", this.semesterService.listOfAllSemesters());

        return "section";
    }

    @RequestMapping(value = "/sections/add", method = RequestMethod.POST)
    public String addSection(@ModelAttribute("id") int id, @ModelAttribute("faculty") Faculty faculty, @ModelAttribute("semester") Semester semester,
            @ModelAttribute("yogaClass") YogaClass yogaClass, @ModelAttribute("location") String location, Model model) {
        String error = "";

        try {
           // Semester semester = semesterService.getSemeterByID(s.getSemester().getId());
            //YogaClass yogaClass = yogaClassService.getYogaClassByID(s.getYogaClass().getId());
            //Faculty faculty = facultyService.getFacultyByID(s.getFaculty().getId());

            if (id == 0) {
                Section sec = new Section();
                sec.setLocation(location);
                this.yogaSectionService.createNewSection(yogaClass, semester, faculty, sec);
                model.addAttribute("msg", "Section is successfully created");
            } else {
                Section section = yogaSectionService.getSectionByID(id);
               // section.setStart(s.getStart());
                //section.setEnd(s.getEnd());
                this.yogaSectionService.updateSection(section);
                model.addAttribute("msg", "Yoga Section is successfully updated");
            }
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (RecordAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("section", new Section());
        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        model.addAttribute("listSemesters", this.semesterService.listOfAllSemesters());
        return "redirect:/sections";
    }

}
