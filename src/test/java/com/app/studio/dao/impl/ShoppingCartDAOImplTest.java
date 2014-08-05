package com.app.studio.dao.impl;

import com.app.studio.dao.ProductDAO;
import com.app.studio.dao.ShoppingCartDAO;
import com.app.studio.model.Product;
import com.app.studio.model.ShoppingCart;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private ProductDAO productDAO;

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
     * Test of createAssociation method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testCreateAssociations() {

        System.out.println("Add new ShoppingCart");
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product();
        product.setName("HP");
        product.setType("Computer");
        product.setPrice(1300);
        product.setAvailableQuantity(3);
        product.setDescription("HP123");

        Product resultProduct = productDAO.create(product);
        shoppingCart.addProduct(product);
        assertNotNull(resultProduct.getId());
        assertEquals(product, resultProduct);

        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());
        assertEquals(expect, shoppingCart);

        ShoppingCart shoppingCartTest = shoppingCartDAO.getById(expect.getId());
        Set<Product> shoppingCartList = shoppingCartTest.getSetOfProducts();
        for (Product product1 : shoppingCartList) {
            if (product1.equals(product)) {
                assertEquals(product1, product);
            }
        }

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

    /**
     * Test of createAssociation method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testRemoveAssociations() {

        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product();
        product.setName("HP");
        product.setType("Computer");
        product.setPrice(1300);
        product.setAvailableQuantity(3);
        product.setDescription("HP123");

        Product resultProduct = productDAO.create(product);
        shoppingCart.addProduct(product);
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);

        ShoppingCart removeShoppingCart = shoppingCartDAO.remove(expect.getId());
        for (Product removeProduct : removeShoppingCart.getSetOfProducts()) {
            try {
                System.out.println("Checking Product" + removeProduct.getId());
                Product nullResult = productDAO.getById(removeProduct.getId());
                assertNull(nullResult);
                System.out.println("Null: Product" + removeProduct.getId());
            } catch (Exception e) {
                assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
                System.out.println("Not found: Product " + removeProduct.getId());
            }
        }
    }
}
