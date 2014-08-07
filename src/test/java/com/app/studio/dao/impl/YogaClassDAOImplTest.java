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
public class YogaClassDAOImplTest {

    @Autowired
    private YogaClassDAO yogaClassDAO;

    @Autowired
    private SectionDAO sectionDAO;

    @Autowired
    private SemesterDAO semesterDAO;

    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private UserDAO userDAO;

    public YogaClassDAOImplTest() {
    }

    /**
     * Test of create method, of class YogaClassDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Add new YogaClass");
        YogaClass yogaclass = new YogaClass();
        YogaClass expect = yogaClassDAO.create(yogaclass);
        assertNotNull(expect.getId());
    }

    /**
     * Test of update method, of class YogaClassDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update Yoga Class");
        YogaClass yogaclass = new YogaClass();
        yogaclass.setName("Software Engineering");
        yogaclass.setPrice(14);
        yogaclass = yogaClassDAO.update(yogaclass);
        assertNotNull(yogaclass);

    }

    /**
     * Test of list method, of class YogaClassDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("List the Yoga Classes");
        YogaClass yogaclass = new YogaClass();
        YogaClass expect = yogaClassDAO.create(yogaclass);
        assertNotNull(expect.getId());

        List<YogaClass> result = yogaClassDAO.list();
        assertEquals(1, result.size());
    }

    /**
     * Test of getById method, of class YogaClassDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("Yoga Class getByID");
        YogaClass yogaclass = new YogaClass();
        YogaClass expect = yogaClassDAO.create(yogaclass);
        assertNotNull(expect.getId());

        YogaClass result = yogaClassDAO.getById(expect.getId());
        assertEquals(expect, result);
    }

    /**
     * Test of remove method, of class YogaClassDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("List the class to remove");
        YogaClass yogaclass = new YogaClass();
        YogaClass expect = yogaClassDAO.create(yogaclass);
        assertNotNull(expect.getId());

        yogaClassDAO.remove(expect.getId());
    }

    @Test
    public void testCreateAssociations() {

        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (YogaClass)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (Faculty)
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
        "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);

        Faculty expectFaculty = new Faculty(expectUser);
        expectFaculty = facultyDAO.create(expectFaculty);

        // Create associated entities (Sections) 
        Section section1 = new Section(expectSemester, expectYogaclass, expectFaculty);
        section1.setLocation("Room1");
        section1.setSchedule("M 1700-1800");
        section1.setMaxStudents(20);
        section1 = sectionDAO.create(section1);

        Section section2 = new Section(expectSemester, expectYogaclass, expectFaculty);
        section2.setLocation("Room2");
        section2.setSchedule("M 1700-1800");
        section2.setMaxStudents(20);
        section2 = sectionDAO.create(section2);

        Set<Section> expectSetOfSections = expectYogaclass.getSetOfSections();

        // Check if entities are the same
        YogaClass resultYogaClass = yogaClassDAO.getById(expectYogaclass.getId());
        assertEquals(expectYogaclass, resultYogaClass);

        // Check if size of set is the same
        Set<Section> resultSetOfSections = resultYogaClass.getSetOfSections();
        assertEquals(expectSetOfSections.size(), resultSetOfSections.size());
        System.out.println("Num of Sections: " + resultSetOfSections.size());

        // Check if set contents are the same
        for (Section expectSection : expectSetOfSections) {
            boolean found = false;
            for (Section resultSection : resultSetOfSections) {
                if (expectSection.equals(resultSection)) {
                    found = true;
                    break;
                }
            }
            assertEquals(true, found);
        }

    }

    @Test
    public void testRemoveAssociations() {
        
        // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create associated entities (YogaClass)
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

        // Create associated entities (User)
        User user1 = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        User user2 = new User("Juan", "Calles", "Juan", "Calles", "What is your favorit car?", "Corolla");
        user1 = userDAO.create(user1);
        user2 = userDAO.create(user2);

        // Create associated entities (Faculty)
        Faculty faculty1 = new Faculty(user1);
        faculty1 = facultyDAO.create(faculty1);
        Faculty faculty2 = new Faculty(user2);
        faculty2 = facultyDAO.create(faculty2);

        // Create associated entities (Sections) 
        Section section1 = new Section(expectSemester, expectYogaclass, faculty1);
        section1.setLocation("Room1");
        section1.setSchedule("M 1700-1800");
        section1.setMaxStudents(20);
        section1 = sectionDAO.create(section1);

        Section section2 = new Section(expectSemester, expectYogaclass, faculty2);
        section2.setLocation("Room2");
        section2.setSchedule("M 1700-1800");
        section2.setMaxStudents(20);
        section2 = sectionDAO.create(section2);

        YogaClass removedYogaClass = yogaClassDAO.remove(expectYogaclass.getId());
        for (Section removedSection : expectYogaclass.getSetOfSections()) {
            try {
                System.out.println("Checking section " + removedSection.getId());
                Section nullResult = sectionDAO.getById(removedSection.getId());
                assertNull(nullResult);
                System.out.println("Null: section " + removedSection.getId());
            } catch (Exception e) {
                assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
                System.out.println("Not found: section " + removedSection.getId());
            }

        }
    }
    
    @Test
    public void TestPrerequisites()
    {
        
       // Create associated entities (Semester)
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);

        // Create Yoga Classes
        
        YogaClass yogaClass1 = new YogaClass();
        yogaClass1.setName("Yoga Principles ");
        yogaClass1.setPrice(10);
        yogaClass1 = yogaClassDAO.create(yogaClass1);
        
        YogaClass yogaClass2 = new YogaClass();
        yogaClass2.setName("Yoga Principles II");
        yogaClass2.setPrice(10);
        yogaClass2 = yogaClassDAO.create(yogaClass2);
        
        YogaClass yogaClass3 = new YogaClass();
        yogaClass3.setName("Yoga Principles III");
        yogaClass3.setPrice(10);
        yogaClass3.addPrerequisite(yogaClass1);
        yogaClass3.addPrerequisite(yogaClass2);
        yogaClass3 = yogaClassDAO.create(yogaClass3);
        
        Set<YogaClass> expectYogaClasses= yogaClass3.getSetOfPrerequisites();
        
        // Check if entities are the same
        YogaClass resultYogaClass = yogaClassDAO.getById(yogaClass3.getId());
        assertEquals(yogaClass3, resultYogaClass);
    
       // Check if size of set is the same
        Set<YogaClass> resultSetOfClasses = resultYogaClass.getSetOfPrerequisites();
        assertEquals(expectYogaClasses.size(), resultSetOfClasses.size());
        System.out.println("Num of Classes: " + resultSetOfClasses.size());

      

        
    }
    
    
}
