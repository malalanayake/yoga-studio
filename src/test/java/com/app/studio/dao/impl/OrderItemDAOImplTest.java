package com.app.studio.dao.impl;

import com.app.studio.dao.OrderItemDAO;
import com.app.studio.model.OrderItem;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ahmadreza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class OrderItemDAOImplTest {

    @Autowired
    private OrderItemDAO orderItemDAO;

    /**
     * Test of create method, of class OrderItemDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create OrderItem");
        OrderItem o = new OrderItem();
        o.setQuantity(3);
        OrderItem result = orderItemDAO.create(o);
        assertNotNull(result);
        assertEquals(o, result);
    }

    /**
     * Test of update method, of class OrderItemDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update OrderItem");
        OrderItem o = new OrderItem();
        o.setQuantity(3);
        o = orderItemDAO.create(o);
        assertNotNull(o);
        OrderItem result = orderItemDAO.update(o);
        assertEquals(o, result);
    }

    /**
     * Test of getById method, of class OrderItemDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("OrderItem getById");
        OrderItem o = new OrderItem();
        o.setQuantity(3);
        o = orderItemDAO.create(o);
        assertNotNull(o);

        int id = o.getId();
        OrderItem result = orderItemDAO.getById(id);
        assertEquals(o, result);
    }

    /**
     * Test of remove method, of class OrderItemDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Remove OrderItem");
        OrderItem o = new OrderItem();
        o.setQuantity(3);
        o = orderItemDAO.create(o);
        assertNotNull(o);

        int id = o.getId();
        OrderItem result = orderItemDAO.remove(id);
        assertEquals(o, result);

        try {
            OrderItem nullResult = orderItemDAO.getById(id);
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }
    }
}
