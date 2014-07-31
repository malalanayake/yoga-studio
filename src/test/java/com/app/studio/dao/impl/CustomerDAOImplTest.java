package com.app.studio.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.model.Customer;

/**
 *
 * @author malalanayake
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class CustomerDAOImplTest {

    @Autowired
    private CustomerDAO customerDAO;

    public CustomerDAOImplTest() {
    }

    /**
     * Test of addCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        Customer p = new Customer();
        p.setName("Malinda");
        p.setAddress("Colombo");
        p = customerDAO.addCustomer(p);
        assertNotNull(p.getId());
    }

    /**
     * Test of updateCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testUpdateCustomer() {
        System.out.println("addCustomer");
        Customer p = new Customer();
        p.setName("Malinda");
        p.setAddress("Colombo");
        p = customerDAO.addCustomer(p);
        assertNotNull(p.getId());

        p.setName("Juan");
        Customer cus = customerDAO.updateCustomer(p);
        assertEquals("Juan", cus.getName());
    }

}
