package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.WaitingRequestDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Customer;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.User;
import com.app.studio.model.WaitingRequest;
import com.app.studio.model.YogaClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jCalles
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class WaitingRequestDAOImplTest {

    public WaitingRequestDAOImplTest() {
    }

    @Autowired
    private WaitingRequestDAO waitingRequestDAO;
    @Autowired
    private SemesterDAO semesterDAO;
    @Autowired
    private SectionDAO sectionDAO;
    @Autowired
    private YogaClassDAO yogaClassDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomerDAO customerDAO;

    /**
     * Test of create method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testCreate() {

        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (Yoga Class)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (Section)
        Section expectSection = new Section(expectSemester, expectYogaclass);
        expectSection.setLocation("Room1");
        expectSection.setSchedule("M 1700-1800");
        expectSection.setMaxStudents(20);
        expectSection = sectionDAO.create(expectSection);

        // Create associated entities (User)
        User user1 = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        User user2 = new User("Juan", "Calles", "Juan", "Calles", "What is your favorit car?", "Corolla");
        user1 = userDAO.create(user1);
        user2 = userDAO.create(user2);

        // Create associated entities (Customer)
        Customer customer1 = new Customer(user1);
        Customer customer2 = new Customer(user2);
        customer1 = customerDAO.create(customer1);
        customer2 = customerDAO.create(customer2);

        System.out.println("Create Waiting Request");
        WaitingRequest waitingRequest = new WaitingRequest(expectSection, customer1);
        waitingRequest.setDate("2014-03-03");
        WaitingRequest result = waitingRequestDAO.create(waitingRequest);
        assertNotNull(result);
        assertEquals(waitingRequest, result);
    }

    /**
     * Test of update method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testUpdate() {

        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (Yoga Class)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (Section)
        Section expectSection = new Section(expectSemester, expectYogaclass);
        expectSection.setLocation("Room1");
        expectSection.setSchedule("M 1700-1800");
        expectSection.setMaxStudents(20);
        expectSection = sectionDAO.create(expectSection);

        // Create associated entities (User)
        User user1 = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        User user2 = new User("Juan", "Calles", "Juan", "Calles", "What is your favorit car?", "Corolla");
        user1 = userDAO.create(user1);
        user2 = userDAO.create(user2);

        // Create associated entities (Customer)
        Customer customer1 = new Customer(user1);
        Customer customer2 = new Customer(user2);
        customer1 = customerDAO.create(customer1);
        customer2 = customerDAO.create(customer2);

 
        System.out.println("Create Waiting Request");
        WaitingRequest waitingRequest = new WaitingRequest(expectSection, customer1);
        waitingRequest.setDate("2014-03-03");
        WaitingRequest result = waitingRequestDAO.update(waitingRequest);
        assertNotNull(result);
        assertEquals(waitingRequest, result);

    }

    /**
     * Test of getById method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testGetById() {

        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (Yoga Class)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (Section)
        Section expectSection = new Section(expectSemester, expectYogaclass);
        expectSection.setLocation("Room1");
        expectSection.setSchedule("M 1700-1800");
        expectSection.setMaxStudents(20);
        expectSection = sectionDAO.create(expectSection);

        // Create associated entities (User)
        User user1 = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        User user2 = new User("Juan", "Calles", "Juan", "Calles", "What is your favorit car?", "Corolla");
        user1 = userDAO.create(user1);
        user2 = userDAO.create(user2);

        // Create associated entities (Customer)
        Customer customer1 = new Customer(user1);
        Customer customer2 = new Customer(user2);
        customer1 = customerDAO.create(customer1);
        customer2 = customerDAO.create(customer2);

        System.out.println("Waiting Request getById");
        WaitingRequest waitingRequest = new WaitingRequest(expectSection, customer1);
        waitingRequest.setDate("2014-07-07");
        waitingRequest = waitingRequestDAO.create(waitingRequest);
        assertNotNull(waitingRequest);

        int id = waitingRequest.getId();
        WaitingRequest result = waitingRequestDAO.getById(id);
        assertEquals(waitingRequest, result);
    }

    /**
     * Test of remove method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testRemove() {

        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (Yoga Class)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (Section)
        Section expectSection = new Section(expectSemester, expectYogaclass);
        expectSection.setLocation("Room1");
        expectSection.setSchedule("M 1700-1800");
        expectSection.setMaxStudents(20);
        expectSection = sectionDAO.create(expectSection);

        // Create associated entities (User)
        User user1 = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        user1 = userDAO.create(user1);

        // Create associated entities (Customer)
        Customer customer1 = new Customer(user1);
        customer1 = customerDAO.create(customer1);

        System.out.println("Remove Waiting Request");
        WaitingRequest waitingRequest = new WaitingRequest(expectSection, customer1);

        waitingRequest = waitingRequestDAO.create(waitingRequest);
        assertNotNull(waitingRequest);
        int id = waitingRequest.getId();
        WaitingRequest result = waitingRequestDAO.remove(id);
        assertEquals(waitingRequest, result);

        try {
            WaitingRequest nullResult = waitingRequestDAO.getById(id);
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }

    }

    @Test
    public void testCreateAssociations() {

        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (Yoga Class)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (Section)
        Section expectSection = new Section(expectSemester, expectYogaclass);
        expectSection.setLocation("Room1");
        expectSection.setSchedule("M 1700-1800");
        expectSection.setMaxStudents(20);
        expectSection = sectionDAO.create(expectSection);

        // Create associated entities (User)
        User user1 = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        User user2 = new User("Juan", "Calles", "Juan", "Calles", "What is your favorit car?", "Corolla");
        user1 = userDAO.create(user1);
        user2 = userDAO.create(user2);

        // Create associated entities (Customer)
        Customer customer1 = new Customer(user1);
        Customer customer2 = new Customer(user2);
        customer1 = customerDAO.create(customer1);
        customer2 = customerDAO.create(customer2);

        // Create associated entities (Waiting Request)
        WaitingRequest waitingRequest1 = new WaitingRequest(expectSection, customer1);
        waitingRequest1.setDate("2014-08-08");
        waitingRequest1 = waitingRequestDAO.create(waitingRequest1);

        WaitingRequest waitingRequest2 = new WaitingRequest(expectSection, customer2);
        waitingRequest2.setDate("2014-08-08");
        waitingRequest2 = waitingRequestDAO.create(waitingRequest2);

        Set<WaitingRequest> expectSetOfWaitingRequests = expectSection.getSetOfWaitingRequests();

        // Check if entities are the same
        WaitingRequest resultwaitingRequest = waitingRequestDAO.getById(waitingRequest1.getId());
        assertEquals(waitingRequest1, resultwaitingRequest);

        // Check if size of set is the same
        Set<WaitingRequest> resultSetOfWaitingRequest = expectSection.getSetOfWaitingRequests();
        assertEquals(resultSetOfWaitingRequest.size(), expectSetOfWaitingRequests.size());
        System.out.println("Num of Waiting Requests: " + expectSetOfWaitingRequests.size());

        // Check if set contents are the same
        for (WaitingRequest expectwaitingrequest : expectSetOfWaitingRequests) {
            boolean found = false;
            for (WaitingRequest resultwaitingrequest : resultSetOfWaitingRequest) {
                if (expectwaitingrequest.equals(resultwaitingrequest)) {
                    found = true;
                    break;
                }
            }
            assertEquals(true, found);
        }
    }

}
