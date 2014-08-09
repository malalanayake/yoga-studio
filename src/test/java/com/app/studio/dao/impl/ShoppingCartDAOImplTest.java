package com.app.studio.dao.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.ProductDAO;
import com.app.studio.dao.ShoppingCartDAO;
import com.app.studio.dao.ShoppingCartItemDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.model.Customer;
import com.app.studio.model.Product;
import com.app.studio.model.ShoppingCart;
import com.app.studio.model.ShoppingCartItem;
import com.app.studio.model.User;
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
    private ShoppingCartItemDAO shoppingCartItemDAO;

    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private UserDAO userDAO; 
    
    @Autowired
    private CustomerDAO customerDAO;

    public ShoppingCartDAOImplTest() {
    }

    /**
     * Test of create method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Add new ShoppingCart");
        
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        ShoppingCart shoppingCart = new ShoppingCart(expectCustomer);
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());
    }

    /**
     * Test of createAssociation method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testCreateAssociations() {

        System.out.println("Add new ShoppingCart");
        
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        ShoppingCart shoppingCart = new ShoppingCart(expectCustomer);
        
        Product product = new Product();
        product.setName("HP");
        product.setType("Computer");
        product.setPrice(1300);
        product.setAvailableQuantity(3);
        product.setDescription("HP123");

        Product resultProduct = productDAO.create(product);
        
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        cartItem = shoppingCartItemDAO.create(cartItem);
        
        assertNotNull(resultProduct.getId());
        assertEquals(product, resultProduct);

        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());
        assertEquals(expect, shoppingCart);

        ShoppingCart shoppingCartTest = shoppingCartDAO.getById(expect.getId());
        Set<ShoppingCartItem> shoppingCartList = shoppingCartTest.getSetOfShoppingCartItems();
        for (ShoppingCartItem cartItem1 : shoppingCartList) {
            assertEquals(cartItem1, cartItem);
        }

    }

    /**
     * Test of list method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("List the ShoppingCart");
        
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        ShoppingCart shoppingCart = new ShoppingCart(expectCustomer);
        
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
        
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        ShoppingCart shoppingCart = new ShoppingCart(expectCustomer);
        
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
        
        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        ShoppingCart shoppingCart = new ShoppingCart(expectCustomer);
        
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);
        assertNotNull(expect.getId());

        shoppingCartDAO.remove(expect.getId());
    }

    /**
     * Test of createAssociation method, of class ShoppingCartDAOImpl.
     */
    @Test
    public void testRemoveAssociations() {

        User expectUser = new User("yogamaster", "yogamaster", "Yoga", "Master",
                "What is your favorit car?", "Benz");
        expectUser = userDAO.create(expectUser);
        
        Customer expectCustomer = new Customer(expectUser);
        expectCustomer = customerDAO.create(expectCustomer);
        
        ShoppingCart shoppingCart = new ShoppingCart(expectCustomer);
        
        Product product = new Product();
        product.setName("HP");
        product.setType("Computer");
        product.setPrice(1300);
        product.setAvailableQuantity(3);
        product.setDescription("HP123");

        Product resultProduct = productDAO.create(product);
        
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        cartItem = shoppingCartItemDAO.create(cartItem);
                
        ShoppingCart expect = shoppingCartDAO.create(shoppingCart);

        ShoppingCart removeShoppingCart = shoppingCartDAO.remove(expect.getId());
        for (ShoppingCartItem removeItem : removeShoppingCart.getSetOfShoppingCartItems()) {
            try {
                System.out.println("Checking ShoppingCartItem" + removeItem.getId());
                ShoppingCartItem nullItem = shoppingCartItemDAO.getById(removeItem.getId());
                assertNull(nullItem);
                System.out.println("Null: ShoppingCartItem, " + removeItem.getId());
            } catch (Exception e) {
                assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
                System.out.println("Not found: Product " + removeItem.getId());
            }
        }
    }
}
