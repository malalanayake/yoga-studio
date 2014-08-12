package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.service.FacultyService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which is controlling the faculties
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

    /* ************************* WAIVERS ************************* */
    @RequestMapping(value = "/waivers", method = RequestMethod.GET)
    public String listWaivers(Principal user, Model model) {
        getWaivers(user.getName(), model);
        return "waiver";
    }

    /**
     * Approve waivers
     *
     * @param id
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/waivers/approve/{id}", method = RequestMethod.GET)
    public String approveWaiver(@PathVariable("id") int id, Principal user, Model model) {
        submitWaiverResponse(id, user.getName(), model, true);
        return "waiver";
    }

    /**
     * Reject waivers
     *
     * @param id
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/waivers/reject/{id}", method = RequestMethod.GET)
    public String rejectWaiver(@PathVariable("id") int id, Principal user, Model model) {
        submitWaiverResponse(id, user.getName(), model, false);
        return "waiver";
    }

    private void getWaivers(String username, Model model) {
        try {
            model.addAttribute("listWaivers", this.facultyService.getFacultyByUsername(username).getSetOfWaiverRequests());
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
    }

    private void submitWaiverResponse(int waiverID, String username, Model model, boolean isApproved) {
        try {
            WaiverRequest waiver = this.facultyService.submitWaiverResponse(waiverID, isApproved);
            model.addAttribute("msg", buildWaiverResponseMessage(waiver, isApproved));
            getWaivers(username, model);
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
    }

    private String buildWaiverResponseMessage(WaiverRequest waiver, boolean isApproved) {
        StringBuffer message = new StringBuffer();
        message.append("Waiver request for ").append(waiver.getYogaClass().getName())
                .append(" submitted by ").append(waiver.getCustomer().getUser().getFirstName())
                .append(" ").append(waiver.getCustomer().getUser().getLastName());
        if (isApproved) {
            message.append(" has been approved.");
        } else {
            message.append(" has been rejected.");
        }
        System.out.println("\n###############\n" + message.toString() + "\n###############\n");
        return message.toString();
    }

    /**
     * List Advisees
     *
     * @param user
     * @param model
     * @return
     */
    /* ************************* ADVISEES ************************* */
    @RequestMapping(value = "/advisees", method = RequestMethod.GET)
    public String listAdvisees(Principal user, Model model) {
        try {
            model.addAttribute("listAdvisees", this.facultyService.getFacultyByUsername(user.getName()).getSetOfCustomers());
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "advisee";
    }

    /**
     * Assige Sections
     *
     * @param user
     * @param model
     * @return
     */
    /* ************************* ASSIGNED SECTIONS ************************* */
    @RequestMapping(value = "/assigned-sections", method = RequestMethod.GET)
    public String listSections(Principal user, Model model) {
        getSections(user.getName(), model);
        return "assigned-section";
    }

    /**
     * Show students
     *
     * @param user
     * @param model
     * @return
     */
    /* ************************* STUDENTS ************************* */
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String listStudents(Principal user, Model model) {
        getSections(user.getName(), model);
        return "student";
    }

    private void getSections(String username, Model model) {
        try {
            model.addAttribute("listSections", this.facultyService.getFacultyByUsername(username).getSetOfSections());
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
    }

    /**
     * View Faculties
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/faculties", method = RequestMethod.GET)
    public String requestListOfFaculties(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        return "faculty";
    }

    /**
     * Add or update faculties
     *
     * @param u
     * @param model
     * @return
     */
    @RequestMapping("/faculties/add")
    public String requestToAddNewFaculties(@ModelAttribute("user") User u, Model model) {
        try {
            if (u.getId() == 0) {
                facultyService.createNewFaculty(u);
                model.addAttribute("msg", "Faculty " + u.getFirstName() + " succesfully created");
                model.addAttribute("user", new User());
            } else {
                facultyService.updateFaculty(u);
                model.addAttribute("msg", "Faculty " + u.getFirstName() + " succesfully updated");
                model.addAttribute("user", new User());
            }
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", u);
        } catch (RecordAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", u);
        }
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        return "faculty";
    }

    /**
     * Present faculty data in edit mode
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/faculties/edit/{id}", method = RequestMethod.GET)
    public String requestToUpdateFaculty(@PathVariable("id") int id, Model model) {
        Faculty fac = null;
        try {
            fac = facultyService.getFacultyByID(id);
            if (fac != null) {
                model.addAttribute("user", fac.getUser());
            }
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }

        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        return "faculty";
    }

    /**
     * Remove faculty
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/faculties/remove/{id}", method = RequestMethod.GET)
    public String requestToDeleteFaculty(@PathVariable("id") int id, Model model) {
        Faculty faculty = null;
        try {
            faculty = facultyService.getFacultyByID(id);
            facultyService.deleteFaculty(faculty);
            model.addAttribute("msg", "Faculty " + faculty.getUser().getFirstName() + " succesfully deleted");
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.toString());
        }
        model.addAttribute("user", new User());
        model.addAttribute("listFaculties", this.facultyService.listAllFaculties());
        return "faculty";
    }
}
