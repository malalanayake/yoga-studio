package com.app.studio.dao.impl;

import com.app.studio.dao.OrderDAO;
import com.app.studio.model.Order;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Test of create method, of class OrderDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create Order");
        Order o = new Order();
        o.setStatus("A");
        o.setTotalPrice(1.23);
        Order result = orderDAO.create(o);
        assertNotNull(result);
        assertEquals(o, result);
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

}
