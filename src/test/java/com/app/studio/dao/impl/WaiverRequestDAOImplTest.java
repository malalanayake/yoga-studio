package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Customer;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
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
public class WaiverRequestDAOImplTest {

    @Autowired
    private WaiverRequestDAO waiverRequestDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private YogaClassDAO yogaClassDAO;
    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private UserDAO userDAO;

    public WaiverRequestDAOImplTest() {
    }

    /**
     * Test of create method, of class WaiverRequestDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        userForCustomer = userDAO.create(userForCustomer);
        Customer customer = new Customer(userForCustomer);
        
        Faculty faculty = new Faculty(userForCustomer);
        Faculty faulty = facultyDAO.create(faculty);
        customer.setAdvisor(faulty);
        customer = customerDAO.create(customer);
        
        YogaClass yogaclass = new YogaClass();
        YogaClass expect = yogaClassDAO.create(yogaclass);
        assertNotNull(expect.getId());
        WaiverRequest waiverRequest = new WaiverRequest(yogaclass,customer);
        waiverRequest.setStatus(WaiverRequest.Constants.STATUS_PENDING);
        WaiverRequest result = waiverRequestDAO.create(waiverRequest);
        assertNotNull(result.getId());
    }

}
