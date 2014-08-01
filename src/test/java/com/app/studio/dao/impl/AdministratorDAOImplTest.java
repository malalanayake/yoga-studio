package com.app.studio.dao.impl;

import com.app.studio.dao.AdministratorDAO;
import com.app.studio.model.Administrator;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class AdministratorDAOImplTest {

    @Autowired
    private AdministratorDAO administratorDAO;

    /**
     * Test of add method, of class AdministratorDAOImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Administrator a = new Administrator();
        Administrator result = administratorDAO.create(a);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    /**
     * Test of update method, of class AdministratorDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Administrator a = administratorDAO.create(new Administrator());
        assertNotNull(a.getId());

        int newId = a.getId() + 1;
        a.setId(newId);
        Administrator result = administratorDAO.update(a);
        assertEquals(newId, result.getId());
    }

    /**
     * Test of getById method, of class AdministratorDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Administrator a = administratorDAO.create(new Administrator());
        assertNotNull(a.getId());

        int id = a.getId();
        Administrator result = administratorDAO.getById(id);
        assertEquals(a, result);
    }

    /**
     * Test of remove method, of class AdministratorDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Administrator a = administratorDAO.create(new Administrator());
        assertNotNull(a.getId());

        int id = a.getId();
        Administrator result = administratorDAO.remove(id);
        assertEquals(a, result);
    }

    /**
     * Test of list method, of class AdministratorDAOImpl.
     */
//    @Test
//    public void testList() {
//        System.out.println("list");
//        AdministratorDAOImpl instance = new AdministratorDAOImpl();
//        List<Administrator> expResult = null;
//        List<Administrator> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
