package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.EnrolledSectionDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Customer;
import com.app.studio.model.EnrolledSection;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.User;
import com.app.studio.model.YogaClass;
import java.util.Date;
import java.util.List;
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
public class EnrolledSectionDAOImplTest {

    @Autowired
    private EnrolledSectionDAO enrolledSectionDAO;
    
    @Autowired
    private SectionDAO sectionDAO;
    
    @Autowired
    private CustomerDAO customerDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private FacultyDAO facultyDAO;
    
    @Autowired
    private SemesterDAO semesterDAO;
    
    @Autowired
    private YogaClassDAO yogaClassDAO;

    public EnrolledSectionDAOImplTest() {
    }

    /**
     * Test of create method, of class EnrolledSectionDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create EnrolledSection");
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);
        assertNotNull(sem);
        
        YogaClass yogaclass = new YogaClass();
        yogaclass.setName("Yoga Principles");
        yogaclass.setPrice(10);
        //yogaclass.setLocation("Mc Laughlin Building, 115");
        
        User expectUser2 = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser2);

        Faculty expectFaculty = new Faculty(expectUser2);
        expectFaculty = facultyDAO.create(expectFaculty);
        
        Section s = new Section(sem, yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertEquals(s, result);
        
        
        
        EnrolledSection expResult = new EnrolledSection(expectCustomer,s);
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result1 = enrolledSectionDAO.create(expResult);
        assertNotNull(expResult.getId());
    }
    
    /**
     * Test of createAssociation method, of class EnrollSectionDAOImpl.
     */
   /* @Test
    public void testCreateAssociations() {
        
        System.out.println("Create EnrolledSection");
        
        
                
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);
        Yogaclass.setLocation("Mc Laughlin Building, 115");

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);

        User expectUser2 = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser2);

        Faculty expectFaculty = new Faculty(expectUser2);
        expectFaculty = facultyDAO.create(expectFaculty);

        Section s = new Section(sem, Yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertEquals(s, result);
        
        EnrolledSection expResult = new EnrolledSection();
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result = enrolledSectionDAO.create(expResult);
        assertNotNull(result.getId());
        
    }

    /**
     * Test of update method, of class EnrolledSectionDAOImpl.
     */
   @Test
    public void testUpdate() {
        System.out.println("Update EnrolledSection");
        
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);
        assertNotNull(sem);
        
        YogaClass yogaclass = new YogaClass();
        yogaclass.setName("Yoga Principles");
        yogaclass.setPrice(10);
       // Yogaclass.setLocation("Mc Laughlin Building, 115");
        YogaClass expectYogaclass = new YogaClass();
        yogaclass = yogaClassDAO.create(expectYogaclass);
        
        User expectUser2 = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser2);

        Faculty expectFaculty = new Faculty(expectUser2);
        expectFaculty = facultyDAO.create(expectFaculty);
        
        Section s = new Section(sem, yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertEquals(s, result);
        
        EnrolledSection expResult = new EnrolledSection(expectCustomer,s);
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result1 = enrolledSectionDAO.create(expResult);
        assertNotNull(result1.getId());

        EnrolledSection enrolledSection = enrolledSectionDAO.getById(expResult.getId());
        enrolledSection.setStatus("DROP");
        EnrolledSection finalResult = enrolledSectionDAO.update(enrolledSection);
        assertEquals("DROP", finalResult.getStatus());
    }

    /**
     * Test of list method, of class EnrolledSectionDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("List EnrolledSection");
       
         User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);
        assertNotNull(sem);
        
        YogaClass yogaclass = new YogaClass();
        yogaclass.setName("Yoga Principles");
        yogaclass.setPrice(10);
       // Yogaclass.setLocation("Mc Laughlin Building, 115");
        YogaClass expectYogaclass = new YogaClass();
        yogaclass = yogaClassDAO.create(expectYogaclass);
        
        User expectUser2 = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser2);

        Faculty expectFaculty = new Faculty(expectUser2);
        expectFaculty = facultyDAO.create(expectFaculty);
        
        Section s = new Section(sem, yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertEquals(s, result);
        
        EnrolledSection expResult = new EnrolledSection(expectCustomer,s);
        
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result1 = enrolledSectionDAO.create(expResult);
        assertNotNull(result1.getId());

        List<EnrolledSection> list = enrolledSectionDAO.list();
        assertEquals(1, list.size());
    }

    /**
     * Test of getById method, of class EnrolledSectionDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("GetByID EnrolledSection");
        
         User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);
        assertNotNull(sem);
        
        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);
        //Yogaclass.setLocation("Mc Laughlin Building, 115");
        
        User expectUser2 = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser2);

        Faculty expectFaculty = new Faculty(expectUser2);
        expectFaculty = facultyDAO.create(expectFaculty);
        
        Section s = new Section(sem, Yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertEquals(s, result);
        
        EnrolledSection expResult = new EnrolledSection(expectCustomer,s);
        
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result1 = enrolledSectionDAO.create(expResult);
        assertNotNull(result1.getId());

        EnrolledSection enrolledSection = enrolledSectionDAO.getById(expResult.getId());

        assertEquals(result1, enrolledSection);
    }

    /**
     * Test of remove method, of class EnrolledSectionDAOImpl.
     */
    /*@Test
    public void testRemove() {
        System.out.println("List EnrolledSection");
        EnrolledSection expResult = new EnrolledSection();
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result = enrolledSectionDAO.create(expResult);
        assertNotNull(result.getId());
        enrolledSectionDAO.remove(result.getId());
    }*/

}
