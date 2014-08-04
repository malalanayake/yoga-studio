package com.app.studio.dao.impl;

import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
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
        yogaclass.setLocation("Mc Laughlin 115");
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
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

    // Create associated entities (Sections) 
        Section section1 = new Section(expectSemester, expectYogaclass);
        section1.setLocation("Room1");
        section1.setSchedule("M 1700-1800");
        section1.setMaxStudents(20);
        section1 = sectionDAO.create(section1);

        Section section2 = new Section(expectSemester, expectYogaclass);
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

}
