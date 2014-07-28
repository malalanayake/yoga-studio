/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.spring.dao.impl;

import com.app.spring.dao.CustomerDAO;
import com.app.spring.model.Customer;
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
 *
 * @author malalanayake
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/servlet-context-test.xml"})
@Transactional
public class CustomerDAOImplTest {
    @Autowired
    private CustomerDAO customerDAO;
    
    public CustomerDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
        
    }

    /**
     * Test of listCustomers method, of class CustomerDAOImpl.
     */
    @Test
    public void testListCustomers() {
        
    }

    /**
     * Test of getCustomerById method, of class CustomerDAOImpl.
     */
    @Test
    public void testGetCustomerById() {
        
    }

    /**
     * Test of removeCustomer method, of class CustomerDAOImpl.
     */
    @Test
    public void testRemoveCustomer() {
        
    }
    
}
