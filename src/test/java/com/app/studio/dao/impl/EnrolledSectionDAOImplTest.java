package com.app.studio.dao.impl;

import com.app.studio.dao.EnrolledSectionDAO;
import com.app.studio.model.EnrolledSection;
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

    public EnrolledSectionDAOImplTest() {
    }

    /**
     * Test of create method, of class EnrolledSectionDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create EnrolledSection");
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
        EnrolledSection expResult = new EnrolledSection();
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result = enrolledSectionDAO.create(expResult);
        assertNotNull(result.getId());

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
        EnrolledSection expResult = new EnrolledSection();
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result = enrolledSectionDAO.create(expResult);
        assertNotNull(result.getId());

        List<EnrolledSection> list = enrolledSectionDAO.list();
        assertEquals(1, list.size());
    }

    /**
     * Test of getById method, of class EnrolledSectionDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("GetByID EnrolledSection");
        EnrolledSection expResult = new EnrolledSection();
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result = enrolledSectionDAO.create(expResult);
        assertNotNull(result.getId());

        EnrolledSection enrolledSection = enrolledSectionDAO.getById(expResult.getId());

        assertEquals(result, enrolledSection);
    }

    /**
     * Test of remove method, of class EnrolledSectionDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("List EnrolledSection");
        EnrolledSection expResult = new EnrolledSection();
        expResult.setStatus("Enrolled");
        expResult.setDate(new Date().toString());
        EnrolledSection result = enrolledSectionDAO.create(expResult);
        assertNotNull(result.getId());
        enrolledSectionDAO.remove(result.getId());
    }

}
