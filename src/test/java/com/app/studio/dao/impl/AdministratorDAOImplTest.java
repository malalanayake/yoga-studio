package com.app.studio.dao.impl;

import com.app.studio.dao.AdministratorDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.model.Administrator;
import com.app.studio.model.User;
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
public class AdministratorDAOImplTest {

    @Autowired
    private AdministratorDAO administratorDAO;
    @Autowired
    private UserDAO userDAO;

    /**
     * Test of add method, of class AdministratorDAOImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("Add Administrator");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);
        assertEquals(0, user.getRoles().size());

        Administrator a = new Administrator(user);
        Administrator result = administratorDAO.create(a);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getUser());

        User userNew = userDAO.getById(user.getId());
        assertEquals(1, userNew.getRoles().size());
    }

    /**
     * Test of update method, of class AdministratorDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update Administrator");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);
        Administrator administrator = administratorDAO.create(new Administrator(user));
        assertNotNull(administrator.getId());
        User userOld = administrator.getUser();
        userOld.setPassword("test");
        userDAO.update(userOld);

        Administrator result = administratorDAO.getById(administrator.getId());
        User finalUser = result.getUser();
        assertEquals(userOld.getPassword(), finalUser.getPassword());
    }

    /**
     * Test of getById method, of class AdministratorDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("Administrator getById");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);
        Administrator a = administratorDAO.create(new Administrator(user));
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
        System.out.println("Remove Administrator");
        User user = new User("dinuka", "dinuka", "Dinuka", "Malalanayake",
                "What is your favorit car?", "Benz");
        user = userDAO.create(user);
        Administrator a = administratorDAO.create(new Administrator(user));
        assertNotNull(a.getId());

        User userOld = a.getUser();
        Administrator result = administratorDAO.remove(a.getId());
        assertEquals(a, result);
        User afterRemove = userDAO.getById(userOld.getId());
        assertEquals(userOld, afterRemove);

        try {
            Administrator nullResult = administratorDAO.getById(a.getId());
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }
    }

}
