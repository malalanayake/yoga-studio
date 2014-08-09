package com.app.studio.service.impl;

import com.app.studio.common.DateUtils;
import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.EnrolledSectionDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.exception.UnauthorizedOperation;
import com.app.studio.model.Customer;
import com.app.studio.model.EnrolledSection;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import com.app.studio.service.CustomerService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom user detail service which is going to provide the user details to the
 * spring framework
 *
 * @author malalanayake
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;
    private UserDAO userDAO;
    private SemesterDAO semesterDAO;
    private SectionDAO sectionDAO;
    private FacultyDAO facultyDAO;
    private EnrolledSectionDAO enrolledSectionDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setSemesterDAO(SemesterDAO semesterDAO) {
        this.semesterDAO = semesterDAO;
    }

    public void setSectionDAO(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    public void setFacultyDAO(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;
    }

    public void setEnrolledSectionDAO(EnrolledSectionDAO enrolledSectionDAO) {
        this.enrolledSectionDAO = enrolledSectionDAO;
    }

    //SignUp Service
    @Override
    @Transactional
    public Customer addCustomer(Customer c) throws RequiredDataNotPresent, RecordAlreadyExistException {
        c.setSignUpDate(new Date().toString());
        User u = c.getUser();
        if (!u.getFirstName().equals("") && !u.getLastName().equals("") && !u.getUsername().equals("") && !u.getPassword().equals("")
                && !u.getSequrityQuestion().equals("") && !u.getAnswer().equals("")) {
            if (userDAO.getByUserName(c.getUser().getUsername()) == null) {
                User user = userDAO.create(u);
                c.setUser(user);
                return this.customerDAO.create(c);
            } else {
                throw new RecordAlreadyExistException("User already exists");
            }
        } else {
            throw new RequiredDataNotPresent("You have to complete all the fields");
        }
    }

    @Override
    @Transactional
    public void updateCustomer(Customer c) {
        this.customerDAO.update(c);
    }

    @Override
    @Transactional
    public List<Customer> listCustomers() {
        return this.customerDAO.list();
    }

    @Override
    @Transactional
    public Customer getCustomerById(int id) {
        return this.customerDAO.getById(id);
    }

    @Override
    @Transactional
    public void removeCustomer(int id) {
        this.customerDAO.remove(id);
    }

    @Override
    public WaiverRequest addWaiverRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Customer getCustomerByUsername(String userName) {
        return this.customerDAO.getByUserName(userName);
    }

    @Override
    @Transactional
    public List<Section> listAvailableSections() {
        List<Section> sectionList = new ArrayList<Section>();
        List<Semester> semList = this.semesterDAO.getBySignUpDate();
        if (semList != null) {
            for (Semester sem : semList) {
                sectionList.addAll(sem.getSetOfSections());
            }
        }
        return sectionList;
    }

    @Override
    @Transactional
    public Section getSectionById(int id) throws RequiredDataNotPresent {
        Section section = sectionDAO.getById(id);
        if (section != null) {
            return section;
        } else {
            throw new RequiredDataNotPresent("Primary key not present");
        }
    }
    
    @Override
    @Transactional
    public void waitlist(String username, Section section) throws RequiredDataNotPresent {
        Customer customer = this.customerDAO.getByUserName(username);
        if (customer == null) {
            throw new RequiredDataNotPresent("Customer username not found");
        }
        System.out.println("############## Customer: " + customer.toString());
        System.out.println("############## Section: " + section.toString());
        EnrolledSection waitlisted = new EnrolledSection(customer, section);
        waitlisted.setDate(DateUtils.today());
        waitlisted.setStatus(EnrolledSection.Constants.STATUS_WAITLISTED);
        enrolledSectionDAO.create(waitlisted);
    }

    @Override
    @Transactional
    public boolean enroll(String username, Section section) throws RequiredDataNotPresent, UnauthorizedOperation {
        Customer customer = this.customerDAO.getByUserName(username);
        if (customer == null) {
            throw new RequiredDataNotPresent("Customer username not found");
        }
        YogaClass yogaClass = section.getYogaClass();
        System.out.println("################ Enrolling cust=" + customer.getId() + " to section=" + section.getId());

        // Assign advisor if customer doesn't have one.
        if (customer.getAdvisor() == null) {
            assignAdvisor(customer);
        }

        // Get yoga classes completed by customer
        List<YogaClass> completedClasses = getCompletedClasses(customer);
        System.out.println("################ Completed classes: " + completedClasses.toString());

        // Check if there are prerequisites that have not been taken
        boolean hasMissingPrereq = false;
        for (YogaClass prereq : yogaClass.getSetOfPrerequisites()) {
            if (!completedClasses.contains(prereq)) {
                hasMissingPrereq = true;
                break;
            }
        }
        
        // If there are prerequisites that have not been taken, check waiver.
        if (hasMissingPrereq) {
            checkWaiver(customer, yogaClass);
        }

        // Check if the section still has free slots
        if (isSectionAvailable(section)) {
            EnrolledSection enrolled = new EnrolledSection(customer, section);
            enrolled.setDate(DateUtils.today());
            enrolled.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
            enrolledSectionDAO.create(enrolled);
            System.out.println("################ Created enrollment: " + enrolled.toString());
            return true;
        } else {
            System.out.println("################ Full: " + section.toString());
            return false;
        }
    }

    private void assignAdvisor(Customer cust) throws RequiredDataNotPresent {
        Faculty advisor = this.facultyDAO.getNextAdvisor();
        if (advisor != null) {
            System.out.println("################ Assigning faculty=" + advisor.getId());
            cust.setAdvisor(advisor);
        } else {
            throw new RequiredDataNotPresent("There are no available advisors to be assigned.");
        }
    }

    /**
     * Checks if the customer has an approved waiver for the yoga class. If the
     * waiver is rejected, pending, or not submitted, an UnauthorizedOperation
     * exception is thrown.
     *
     * @param cust
     * @param yogaClass
     * @param missingPrereqs
     * @throws UnauthorizedOperation
     */
    private void checkWaiver(Customer cust, YogaClass yogaClass) throws UnauthorizedOperation {
        boolean hasWaiver = false;
        for (WaiverRequest waiver : cust.getSetOfWaiverRequests()) {
            if (yogaClass.equals(waiver.getYogaClass())) {
                hasWaiver = true;
                if (WaiverRequest.Constants.STATUS_REJECTED.equals(waiver.getStatus())) {
                    // Waiver Rejected
                    throw new UnauthorizedOperation("Your waiver for " + yogaClass.getName() 
                            + " has been rejected. Please select another class to enroll in.");
                } else if (WaiverRequest.Constants.STATUS_PENDING.equals(waiver.getStatus())) {
                    // Waiver Pending
                    throw new UnauthorizedOperation("Your waiver for " + yogaClass.getName() 
                            + " is still pending approval. Please wait for your advisor's approval, or select another class to enroll in.");
                } else {
                    // Waiver Approved
                    System.out.println("################ Waiver Approved for " + yogaClass.getName());
                    break;
                }
            }
        }
        // No waiver submitted
        if (!hasWaiver) {
            throw new UnauthorizedOperation("You are not yet qualified to enroll in " + yogaClass.getName()
                    + " due to incomplete prerequisites. Please submit a waiver request if you wish to enroll in this class.");
        }
    }

    private boolean isSectionAvailable(Section section) {
        int capacity = section.getMaxStudents();
        int numEnrolled = 0;
        for (EnrolledSection enrolled : section.getSetOfEnrolledSections()) {
            if (EnrolledSection.Constants.STATUS_ENROLLED.equals(enrolled.getStatus())) {
                numEnrolled++;
            }
        }
        return numEnrolled < capacity;
    }

    private List<YogaClass> getCompletedClasses(Customer cust) throws UnauthorizedOperation {
        List<YogaClass> completedClasses = new ArrayList<YogaClass>();
        Date today = new Date();
        for (EnrolledSection enrolledSection : cust.getSetOfEnrolledSections()) {
            if (EnrolledSection.Constants.STATUS_ENROLLED.equals(enrolledSection.getStatus())) {
                try {
                    Date enrolledEnd = DateUtils.parse(enrolledSection.getSection().getSemester().getEnddate());
                    if (enrolledEnd.before(today)) {
                        completedClasses.add(enrolledSection.getSection().getYogaClass());
                    }
                } catch (ParseException ex) {
                    throw new UnauthorizedOperation("Error parsing section end date");
                }
            }
        }
        return completedClasses;
    }

}
