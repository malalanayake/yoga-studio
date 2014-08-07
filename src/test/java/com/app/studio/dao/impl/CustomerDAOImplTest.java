package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.EnrolledSectionDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Customer;
import com.app.studio.model.EnrolledSection;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class CustomerDAOImplTest {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private WaiverRequestDAO waiverRequestDAO;
    @Autowired
    private YogaClassDAO yogaClassDAO;
    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private SectionDAO sectionDAO;
    @Autowired
    private SemesterDAO semesterDAO;
    @Autowired
    private EnrolledSectionDAO enrolledSectionDAO;

    /**
     * Test of addCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testAdd() {
        User user = new User("yogaadvisor", "yogaadvisor", "Yoga", "Advisor",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);

        Faculty advisor = new Faculty(user);
        advisor = facultyDAO.create(advisor);

        System.out.println("addCustomer");
        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        userForCustomer = userDAO.create(userForCustomer);
        Customer cus = new Customer(userForCustomer);
        cus.setAddress("Colombo");
        cus.setSignUpDate(new Date().toString());
        cus.setAdvisor(advisor);
        cus = customerDAO.create(cus);
        assertNotNull(cus.getId());

        // Check user association
        User resultUser = userDAO.getByUserName("dinuka1");
        assertNotNull(resultUser);
        List<User> userList = userDAO.list();
        assertEquals(2, userList.size());

        // Check waiver request assocition
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        WaiverRequest waiver = new WaiverRequest(expectYogaclass, cus);
        waiver.setStatus(WaiverRequest.Constants.STATUS_PENDING);
        waiver = waiverRequestDAO.create(waiver);

        WaiverRequest resultWaiver = waiverRequestDAO.getById(waiver.getId());
        assertEquals(cus, resultWaiver.getCustomer());

        // Check enrolled section
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty teacher = new Faculty(expectUser);
        teacher = facultyDAO.create(teacher);

        Section section = new Section(sem, Yogaclass, teacher);
        section.setLocation("Room1");
        section.setSchedule("M 1700-1800");
        section.setMaxStudents(20);
        section = sectionDAO.create(section);

        EnrolledSection enrolledSection = new EnrolledSection(cus, section);
//        enrolledSection.setCustomer(cus);
//        enrolledSection.setSection(section);
        enrolledSection = enrolledSectionDAO.create(enrolledSection);
        assertEquals(enrolledSection, enrolledSectionDAO.getById(enrolledSection.getId()));

    }

    /**
     * Test of updateCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("updateCustomer");
        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        userForCustomer = userDAO.create(userForCustomer);
        Customer p = new Customer(userForCustomer);
        p.setAddress("Colombo");
        p = customerDAO.create(p);
        assertNotNull(p.getId());

        p.setAddress("Ames");
        Customer cus = customerDAO.update(p);
        assertEquals(cus.getAddress(), "Ames");
    }

    @Test
    public void testGetById() {
        System.out.println("updateCustomer");
        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        userForCustomer = userDAO.create(userForCustomer);
        Customer p = new Customer(userForCustomer);
        p.setAddress("Colombo");
        p = customerDAO.create(p);

        Customer result = customerDAO.getById(p.getId());
        assertEquals(p, result);
    }

    @Test
    public void testRemoveAssociations() {
        User user = new User("yogaadvisor", "yogaadvisor", "Yoga", "Advisor",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);

        Faculty advisor = new Faculty(user);
        advisor = facultyDAO.create(advisor);

        User userForCustomer = new User("dinuka1", "dinuka1", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        userForCustomer = userDAO.create(userForCustomer);
        Customer cus = new Customer(userForCustomer);
        cus.setAddress("Colombo");
        cus.setSignUpDate(new Date().toString());
        cus.setAdvisor(advisor);
        cus = customerDAO.create(cus);

        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        WaiverRequest waiver = new WaiverRequest(expectYogaclass, cus);
        waiver.setStatus(WaiverRequest.Constants.STATUS_PENDING);
        waiver = waiverRequestDAO.create(waiver);

        // Check enrolled section
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty teacher = new Faculty(expectUser);
        teacher = facultyDAO.create(teacher);

        Section section = new Section(sem, expectYogaclass, teacher);
        section.setLocation("Room1");
        section.setSchedule("M 1700-1800");
        section.setMaxStudents(20);
        section = sectionDAO.create(section);

        EnrolledSection enrolledSection = new EnrolledSection(cus, section);
//        enrolledSection.setCustomer(cus);
//        enrolledSection.setSection(section);
        enrolledSection = enrolledSectionDAO.create(enrolledSection);

        Customer removedCus = customerDAO.remove(cus.getId());
        try {
            Customer nullCus = customerDAO.getById(cus.getId());
            assertNull(nullCus);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
            System.out.println("Not found: customer " + cus.getId());
        }

//        try {
//            System.out.println("Checking waiver " + waiver.getId());
//            WaiverRequest nullResult = waiverRequestDAO.getById(waiver.getId());
//            System.out.println("waiver id result" + nullResult.getId());
//            assertNull(nullResult);
//            System.out.println("Yikes! NOT Null!!! waiver " + waiver.getId());
//        } catch (Exception e) {
//            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
//            System.out.println("Not found: waiver " + waiver.getId());
//        }
        System.out.println("Checking advisor " + advisor.getId());
        Faculty facultyResult = facultyDAO.getById(advisor.getId());
        assertNotNull(facultyResult);

//        try {
//            System.out.println("Checking enrolledSection " + enrolledSection.getId());
//            EnrolledSection enrolledSectionResult = enrolledSectionDAO.getById(enrolledSection.getId());
//            assertNull(enrolledSectionResult);
//            System.out.println("Yikes! Not Null: enrolledSectionResult " + enrolledSectionResult.getId());
//        } catch (Exception e) {
//            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
//            System.out.println("Not found: enrolledSectionResult " + enrolledSection.getId());
//        }
    }
}
