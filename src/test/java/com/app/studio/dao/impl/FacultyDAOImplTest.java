package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.model.Customer;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.security.Roles;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author malalanayake
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class FacultyDAOImplTest {

    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private WaiverRequestDAO waiverRequestDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private SectionDAO sectionDAO;

    public FacultyDAOImplTest() {
    }

    /**
     * Test of create method, of class FacultyDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create Faculty");

        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);

        Faculty faculty = new Faculty(user);
        Faculty result = facultyDAO.create(faculty);
        assertTrue(result.getUser().getRoles().contains(Roles.ROLE_FACULTY));
    }

    /**
     * Test of update method, of class FacultyDAOImpl.
     */
    @Test
    public void testUpdate() {
        //Create associated data 
        System.out.println("Update Faculty");

        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);

        Faculty faculty = new Faculty(user);
        Faculty result = facultyDAO.create(faculty);

        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        Customer customer = new Customer(userForCustomer);
        customer = customerDAO.create(customer);
        customer.setAdvisor(result);
        customer = customerDAO.update(customer);

        WaiverRequest waiverRequest = new WaiverRequest();
        waiverRequest = waiverRequestDAO.create(waiverRequest);
        Section section = new Section();
        section = sectionDAO.create(section);

        faculty.addWaiverRequest(waiverRequest);
        faculty.addCustomer(customer);
        faculty.addsection(section);
        faculty = facultyDAO.update(faculty);

        assertNotNull(result.getId());

        User userOld = result.getUser();
        assertEquals(user, userOld);

        Faculty forLists = facultyDAO.getById(result.getId());
        //Check list of customers
        assertEquals(1, forLists.getSetOfCustomers().size());
        Iterator<Customer> iteForCustomer = forLists.getSetOfCustomers().iterator();
        while (iteForCustomer.hasNext()) {
            Customer cus = iteForCustomer.next();
            System.out.println("Cheking customer data");
            assertNotNull(cus.getId());

            Customer customerFromDAO = customerDAO.getById(cus.getId());
            assertEquals(cus, customerFromDAO);
            assertEquals(forLists, customerFromDAO.getAdvisor());
        }

        //Check waiver requests
        assertEquals(1, forLists.getSetOfWaiverRequests().size());
        Iterator<WaiverRequest> iteForWaiverRequest = forLists.getSetOfWaiverRequests().iterator();
        while (iteForWaiverRequest.hasNext()) {
            WaiverRequest waiverReq = iteForWaiverRequest.next();
            System.out.println("Cheking waiver request data");
            assertNotNull(waiverReq.getId());

            WaiverRequest requestFromDAO = waiverRequestDAO.getById(waiverReq.getId());
            assertEquals(waiverReq, requestFromDAO);
        }

        assertEquals(1, forLists.getSetOfSections().size());
        Iterator<Section> iteForSections = forLists.getSetOfSections().iterator();
        while (iteForSections.hasNext()) {
            Section sectionReq = iteForSections.next();
            System.out.println("Cheking sections data");
            assertNotNull(sectionReq.getId());

            Section sectionFromDAO = sectionDAO.getById(sectionReq.getId());
            assertEquals(sectionReq, sectionFromDAO);
        }
    }

    /**
     * Test of list method, of class FacultyDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("List Faculty");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);

        Faculty faculty = new Faculty(user);
        Faculty result = facultyDAO.create(faculty);
        assertEquals(1, facultyDAO.list().size());
    }

    /**
     * Test of getById method, of class FacultyDAOImpl.
     */
    @Test
    public void testGetById() {

    }

    /**
     * Test of remove method, of class FacultyDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Remove Faculty");

        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);

        Faculty faculty = new Faculty(user);
        Faculty result = facultyDAO.create(faculty);

        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        Customer customer = new Customer(userForCustomer);
        customer = customerDAO.create(customer);
        customer.setAdvisor(result);
        customer = customerDAO.update(customer);

        WaiverRequest waiverRequest = new WaiverRequest();
        waiverRequest = waiverRequestDAO.create(waiverRequest);
        Section section = new Section();
        section = sectionDAO.create(section);

        faculty.addWaiverRequest(waiverRequest);
        faculty.addCustomer(customer);
        faculty.addsection(section);
        faculty = facultyDAO.update(faculty);

        assertNotNull(result.getId());

        faculty = facultyDAO.remove(faculty.getId());
    }

}
