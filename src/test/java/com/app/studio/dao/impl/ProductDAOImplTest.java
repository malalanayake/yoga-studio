package com.app.studio.dao.impl;

import com.app.studio.dao.ProductDAO;
import com.app.studio.model.Product;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aTabibi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    /**
     * Test of add method, of class ProductDAOImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Product p = new Product();
        Product result = productDAO.create(p);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    /**
     * Test of getById method, of class ProductDAOTest.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Product p = productDAO.create(new Product());
        assertNotNull(p.getId());

        int id = p.getId();
        Product result = productDAO.getById(id);
        assertEquals(p, result);
    }

    /**
     * Test of remove method, of class AdministratorDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Product p = productDAO.create(new Product());
        assertNotNull(p.getId());

        int id = p.getId();
        Product result = productDAO.remove(id);
        assertEquals(p, result);
    }

}
