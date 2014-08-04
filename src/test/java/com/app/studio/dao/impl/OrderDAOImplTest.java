package com.app.studio.dao.impl;

import com.app.studio.dao.OrderDAO;
import com.app.studio.dao.OrderItemDAO;
import com.app.studio.dao.ProductDAO;
import com.app.studio.model.Order;
import com.app.studio.model.OrderItem;
import com.app.studio.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderItemDAO orderItemDAO;

    /**
     * Test of create method, of class OrderDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create Order");
        Order o = new Order();
        o.setStatus("A");
        o.setTotalPrice(1.23);

        Product product = new Product();
        product.setName("HP");
        product.setType("Computer");
        product.setPrice(1300);
        product.setAvailableQuantity(3);
        product.setDescription("HP123");
        Product resultProduct = productDAO.create(product);
        assertNotNull(resultProduct.getId());
        assertEquals(product, resultProduct);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(3);
        orderItem.setProduct(product);

        o.addOrderItem(orderItem);
        Order result = orderDAO.create(o);
        assertNotNull(result.getId());
        assertEquals(o, result);

        Order or = orderDAO.getById(result.getId());
        Set<OrderItem> itemList = or.getSetOfOrderItems();
        for (OrderItem orderItem1 : itemList) {
            if (orderItem1.getProduct().equals(orderItem.getProduct())) {
                assertEquals(orderItem1, orderItem);
            }

        }
    }

    /**
     * Test of update method, of class OrderDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update Order");
        Order o = new Order();
        o.setStatus("A");
        o.setTotalPrice(1.23);
        o = orderDAO.create(o);
        assertNotNull(o);

        o.setStatus("B");
        o.setTotalPrice(4.56);
        Order result = orderDAO.update(o);
        assertEquals(o, result);
    }

    /**
     * Test of list method, of class OrderDAOImpl.
     */
    @Test
    public void testList() {
        System.out.println("Order List");

        List<Order> expResult = new ArrayList<Order>();

        Order o1 = new Order();
        o1.setStatus("1");
        o1.setTotalPrice(1.11);
        expResult.add(orderDAO.create(o1));

        Order o2 = new Order();
        o2.setStatus("2");
        o2.setTotalPrice(2.22);
        expResult.add(orderDAO.create(o2));

        Order o3 = new Order();
        o3.setStatus("3");
        o3.setTotalPrice(3.33);
        expResult.add(orderDAO.create(o3));

        List<Order> result = orderDAO.list();
        assertEquals(expResult, result);
    }

    /**
     * Test of getById method, of class OrderDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("Order getById");
        Order o = new Order();
        o.setStatus("A");
        o.setTotalPrice(1.23);
        o = orderDAO.create(o);
        assertNotNull(o);

        int id = o.getId();
        Order result = orderDAO.getById(id);
        assertEquals(o, result);
    }

    /**
     * Test of remove method, of class OrderDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Remove Order");
        Order o = new Order();
        o.setStatus("A");
        o.setTotalPrice(1.23);
        o = orderDAO.create(o);
        assertNotNull(o);

        int id = o.getId();
        Order result = orderDAO.remove(id);
        assertEquals(o, result);

        try {
            Order nullResult = orderDAO.getById(id);
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    public void testRemoveAssociations() {
        Order o = new Order();
        o.setStatus("A");
        o.setTotalPrice(1.23);

        Product product = new Product();
        product.setName("HP");
        product.setType("Computer");
        product.setPrice(1300);
        product.setAvailableQuantity(3);
        product.setDescription("HP123");
        Product resultProduct = productDAO.create(product);


        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(3);
        orderItem.setProduct(product);

        o.addOrderItem(orderItem);
        Order result = orderDAO.create(o);


        Order or = orderDAO.remove(result.getId());
        for (OrderItem removedOrder : or.getSetOfOrderItems()) {

            try {
                System.out.println("Checking Order " + removedOrder.getId());
                OrderItem nullResult = orderItemDAO.getById(removedOrder.getId());
                assertNull(nullResult);
                System.out.println("Null: order " + removedOrder.getId());
            } catch (Exception e) {
                assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
                System.out.println("Not found: order " + removedOrder.getId());
            }

        }
    }

}
