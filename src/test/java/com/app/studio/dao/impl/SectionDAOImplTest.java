package com.app.studio.dao.impl;

import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.User;
import com.app.studio.model.YogaClass;
import java.util.ArrayList;
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
 * @author Yen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class SectionDAOImplTest {

    @Autowired
    private SectionDAO sectionDAO;

    @Autowired
    private SemesterDAO semesterDAO;

    @Autowired
    private YogaClassDAO yogaClassDAO;

    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * Test of create method, of class SectionDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create Section");

        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty expectFaculty = new Faculty(expectUser);
        expectFaculty = facultyDAO.create(expectFaculty);

        Section s = new Section(sem, Yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertEquals(s, result);
    }

    /**
     * Test of update method, of class SectionDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update Section");

        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty expectFaculty = new Faculty(expectUser);
        expectFaculty = facultyDAO.create(expectFaculty);

        Section s = new Section(sem, Yogaclass, expectFaculty);
        s.setLocation("Room1");
        s.setSchedule("M 1700-1800");
        s.setMaxStudents(20);
        s = sectionDAO.create(s);
        assertNotNull(s);

        s.setLocation("Room2");
        s.setSchedule("F 1800-1900");
        s.setMaxStudents(30);
        Section result = sectionDAO.update(s);
        assertEquals(s, result);
    }

    /**
     * Test of list method, of class SectionDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("Section List");

        List<Section> expResult = new ArrayList<Section>();

        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass yogaclass = new YogaClass();
        yogaclass.setName("Yoga Principles");
        yogaclass.setPrice(10);
        yogaclass = yogaClassDAO.create(yogaclass);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty expectFaculty = new Faculty(expectUser);
        expectFaculty = facultyDAO.create(expectFaculty);

        Section s1 = new Section(sem, yogaclass, expectFaculty);
        s1.setLocation("Room1");
        s1.setSchedule("M 1700-1800");
        s1.setMaxStudents(20);
        expResult.add(sectionDAO.create(s1));

        Section s2 = new Section(sem, yogaclass, expectFaculty);
        s2.setLocation("Room2");
        s2.setSchedule("T 1700-1800");
        s2.setMaxStudents(20);
        expResult.add(sectionDAO.create(s2));

        Section s3 = new Section(sem, yogaclass, expectFaculty);
        s3.setLocation("Room3");
        s3.setSchedule("W 1700-1800");
        s3.setMaxStudents(20);
        expResult.add(sectionDAO.create(s3));

        List<Section> result = sectionDAO.list();
        assertEquals(expResult, result);
    }

    /**
     * Test of getById method, of class SectionDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("Section getById");

        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty expectFaculty = new Faculty(expectUser);
        expectFaculty = facultyDAO.create(expectFaculty);

        Section s = sectionDAO.create(new Section(sem, Yogaclass, expectFaculty));
        assertNotNull(s.getId());

        int id = s.getId();
        Section result = sectionDAO.getById(id);
        assertEquals(s, result);
    }

    /**
     * Test of remove method, of class SectionDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Remove Section");

        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        sem = semesterDAO.create(sem);

        YogaClass Yogaclass = new YogaClass();
        Yogaclass.setName("Yoga Principles");
        Yogaclass.setPrice(10);

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty expectFaculty = new Faculty(expectUser);
        expectFaculty = facultyDAO.create(expectFaculty);

        Section s = sectionDAO.create(new Section(sem, Yogaclass, expectFaculty));
        assertNotNull(s.getId());

        int id = s.getId();
        Section result = sectionDAO.remove(id);
        assertEquals(s, result);

        try {
            Section nullResult = sectionDAO.getById(id);
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }
    }

}
