package com.app.studio.dao.impl;

import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.YogaClass;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNull;
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
public class SemesterDAOImplTest {

    @Autowired
    private SemesterDAO semesterDAO;

    @Autowired
    private SectionDAO sectionDAO;
    
    @Autowired
    private YogaClassDAO yogaClassDAO;
    
   

    public SemesterDAOImplTest() {
    }

    /**
     * Test of create method, of class SemesterDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("addSemester");
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        Semester expectSem = semesterDAO.create(sem);
        assertNotNull(expectSem.getId());
    }

    /**
     * Test of update method, of class SemesterDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("updateSemester");
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        Semester expectSem;
        expectSem = semesterDAO.update(sem);
        assertNotNull(expectSem.getId());
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
        
        Section section1 = new Section(expectSemester, expectYogaclass);
        section1.setLocation("Room1");
        section1.setSchedule("M 1700-1800");
        section1.setMaxStudents(20);
        section1 = sectionDAO.create(section1);

        Section section2 = new Section(expectSemester,expectYogaclass );
        section2.setLocation("Room2");
        section2.setSchedule("M 1700-1800");
        section2.setMaxStudents(20);
        section2 = sectionDAO.create(section2);

        Set<Section> expectSetOfSections = expectSemester.getSetOfSections();

        // Check if entities are the same
        Semester resultSemester = semesterDAO.getById(expectSemester.getId());
        assertEquals(expectSemester, resultSemester);

        // Check if size of set is the same
        Set<Section> resultSetOfSections = resultSemester.getSetOfSections();
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
        Semester expectSemester = new Semester();
        expectSemester.setSignUpDate("2014-03-03");
        expectSemester = semesterDAO.create(expectSemester);
        
       // Create associated entities (Yoga Class)
        
        YogaClass expectYogaclass = new YogaClass();
        expectYogaclass.setName("Yoga Principles");
        expectYogaclass.setPrice(10);
        expectYogaclass.setLocation("Mc Laughlin Building, 115");
        expectYogaclass = yogaClassDAO.create(expectYogaclass);

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

        Semester removedSemester = semesterDAO.remove(expectSemester.getId());
        for (Section removedSection : expectSemester.getSetOfSections()) {
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
}
