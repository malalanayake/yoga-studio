package com.app.studio.dao.impl;

import com.app.studio.dao.ShoppingCartDAO;
import com.app.studio.model.ShoppingCart;
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
public class ShoppingCartDAOImplTest {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartDAOImplTest() {
    }

    /**
     * Test of create method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Add new ShoppingCart");
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());
    }

    /**
     * Test of list method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("List the ShoppingCart");
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());

        List<ShoppingCart> result = shoppingCartDAO.list();
        assertEquals(1, result.size());
    }

    /**
     * Test of getById method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("ShoppingCart getByID");
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());

        ShoppingCart result = shoppingCartDAO.getById(expect.getId());
        assertEquals(expect, result);
    }

    /**
     * Test of remove method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("List the ");
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());
        
        shoppingCartDAO.remove(expect.getId());
    }

}
