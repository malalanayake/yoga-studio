package com.app.studio.dao.impl;

import com.app.studio.dao.UserDAO;
import com.app.studio.model.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test for the UserDAO
 *
 * @author dmalalanayake
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    public UserDAOImplTest() {
    }

    /**
     * Test of create method, of class UserDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create USER");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        user.addRole("FACULTY");
        User expResult = null;
        expResult = userDAO.create(user);
        assertNotNull(expResult.getId());
        assertEquals(expResult.getUsername(), "dinuka");
        assertEquals(expResult.getPassword(), "dinuka");
        assertEquals(expResult.getFirstName(), "Dinuka");
        assertEquals(expResult.getLastName(), "Malalanayake");
        assertEquals(expResult.getSequrityQuestion(), "What is your favorit car?");
        assertEquals(expResult.getAnswer(), "Benz");
        assertEquals(expResult.getRoles().size(), 1);
    }

    /**
     * Test of update method, of class UserDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update USER");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        user.addRole("FACULTY");
        User expResult = userDAO.create(user);
        assertNotNull(expResult.getId());

        expResult.setSequrityQuestion("What is your first Name?");
        User finalUser = userDAO.update(expResult);
        assertEquals(expResult.getSequrityQuestion(), finalUser.getSequrityQuestion());

    }

    /**
     * Test of list method, of class UserDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("USER List");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        user.addRole("FACULTY");
        User expResult = userDAO.create(user);
        assertNotNull(expResult.getId());
        assertEquals(1, expResult.getRoles().size());
        assertEquals(1, userDAO.list().size());
    }

    /**
     * Test of getById method, of class UserDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("User getById");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        user.addRole("FACULTY");
        User expResult = userDAO.create(user);
        assertNotNull(expResult.getId());
        User finalUser = userDAO.getById(expResult.getId());
        assertEquals(expResult, finalUser);
    }

    /**
     * Test of remove method, of class UserDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("User getById");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake", "What is your favorit car?", "Benz");
        user.addRole("FACULTY");
        User expResult = userDAO.create(user);
        assertNotNull(expResult.getId());
        userDAO.remove(expResult.getId());
    }
}
