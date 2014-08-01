package com.app.studio.dao.impl;

import com.app.studio.dao.UserDAO;
import com.app.studio.model.User;
import static org.junit.Assert.*;

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
        System.out.println("create");
        User user = new User();
        user.setUsername("dinuka");
        user.setPassword("dinuka");
        user.setFirstName("Dinuka");
        user.setLastName("Malalanayake");
        user.setSequrityQuestion("What is your favorit car?");
        user.setAnswer("Benz");
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
}
