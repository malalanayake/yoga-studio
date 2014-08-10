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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String requestToListOfSections(Model model) {
        YogaClass yogs = new YogaClass();
        Faculty fac = new Faculty();
        Semester sem = new Semester();
        Section sec = new Section();
        sec.setYogaClass(yogs);
        sec.setFaculty(fac);
        sec.setSemester(sem);
        model.addAttribute("section", sec);
        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        model.addAttribute("listSemesters", this.semesterService.listOfAllSemesters());

        return "section";
    }

    @RequestMapping(value = "/sections/add", method = RequestMethod.POST)
    public String requestToAddNewSection(@ModelAttribute("section") Section s, Model model) {
        String error = "";
        Section section = new Section();
        try {
            if (s.getId() == 0) {
                Semester semester = semesterService.getSemeterByID(s.getSemester().getId());
                YogaClass yogaClass = yogaClassService.getYogaClassByID(s.getYogaClass().getId());
                Faculty faculty = facultyService.getFacultyByID(s.getFaculty().getId());
                this.yogaSectionService.createNewSection(yogaClass, semester, faculty, s);
                model.addAttribute("msg", "Section is successfully created");
            } else {
                section = yogaSectionService.getSectionByID(s.getId());
                section.setLocation(s.getLocation());
                section.setSchedule(s.getSchedule());
                section.setStart(s.getStart());
                section.setEnd(s.getEnd());
                section.setMaxStudents(s.getMaxStudents());
                this.yogaSectionService.updateSection(section);
                model.addAttribute("msg", "Yoga Section is successfully updated");
                model.addAttribute("section", new Section());
            }
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("section", section);

        } catch (RecordAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("section", section);
        }

        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        model.addAttribute("listSemesters", this.semesterService.listOfAllSemesters());
        return "section";
    }

    @RequestMapping(value = "/sections/edit/{id}", method = RequestMethod.GET)
    public String requestToUpdateSection(@PathVariable("id") int id, Model model) {
        Section sec = new Section();
        sec = yogaSectionService.getSectionByID(id);
        model.addAttribute("section", sec);
        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        return "section";
    }

    @RequestMapping(value = "/sections/remove/{id}", method = RequestMethod.GET)
    public String requestToDeleteSection(@PathVariable("id") int id, Model model) {
        Section sec = yogaSectionService.getSectionByID(id);
        try {
            yogaSectionService.deleteSection(sec);
            model.addAttribute("msg", "Yoga Section is successfully deleted");
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.toString());
        }
        model.addAttribute("section", new Section());
        model.addAttribute("listSections", this.yogaSectionService.listOfAllSections());
        model.addAttribute("listYogaClasses", this.yogaClassService.listOfYogaClasses());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        model.addAttribute("listSemesters", this.semesterService.listOfAllSemesters());
        return "section";
    }

}
