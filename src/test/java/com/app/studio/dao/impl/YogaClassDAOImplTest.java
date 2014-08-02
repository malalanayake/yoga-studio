package com.app.studio.dao.impl;

import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.YogaClass;
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
 * @author jCalles
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class YogaClassDAOImplTest {

    @Autowired
    private YogaClassDAO yogaClassDAO;

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

}
