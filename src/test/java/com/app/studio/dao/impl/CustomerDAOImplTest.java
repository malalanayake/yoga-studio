package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.model.Customer;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class CustomerDAOImplTest {

    @Autowired
    private CustomerDAO customerDAO;

    public CustomerDAOImplTest() {
    }

    /**
     * Test of addCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("addCustomer");
        Customer cus = new Customer();
        cus.setAddress("Colombo");
        cus.setSignUpDate(new Date().toString());
        Customer expectCus = customerDAO.create(cus);
        assertNotNull(expectCus.getId());
    }

    /**
     * Test of updateCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("updateCustomer");
        Customer p = new Customer();
        p.setAddress("Colombo");
        p = customerDAO.create(p);
        assertNotNull(p.getId());

        p.setAddress("Ames");
        Customer cus = customerDAO.update(p);
        assertEquals(cus.getAddress(), "Ames");
    }

}
