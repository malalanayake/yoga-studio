package com.app.studio.dao.impl;

import com.app.studio.dao.WaitingRequestDAO;
import com.app.studio.model.WaitingRequest;
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
 * @author jCalles
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-test.xml"})
@Transactional
public class WaitingRequestDAOImplTest {

    public WaitingRequestDAOImplTest() {
    }

    @Autowired
    private WaitingRequestDAO waitingRequestDAO;

    /**
     * Test of create method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create Waiting Request");
        WaitingRequest waitingRequest = new WaitingRequest();
        waitingRequest.setDate("2014-03-03");
        WaitingRequest result = waitingRequestDAO.create(waitingRequest);
        assertNotNull(result);
        assertEquals(waitingRequest, result);
    }

    /**
     * Test of update method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Create Waiting Request");
        WaitingRequest waitingRequest = new WaitingRequest();
        waitingRequest.setDate("2014-03-03");
        WaitingRequest result = waitingRequestDAO.update(waitingRequest);
        assertNotNull(result);
        assertEquals(waitingRequest, result);
    }

    /**
     * Test of getById method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("Waiting Request getById");
        WaitingRequest waitingRequest = new WaitingRequest();
        waitingRequest.setDate("2014-07-07");
        waitingRequest = waitingRequestDAO.create(waitingRequest);
        assertNotNull(waitingRequest);

        int id = waitingRequest.getId();
        WaitingRequest result = waitingRequestDAO.getById(id);
        assertEquals(waitingRequest, result);
    }

    /**
     * Test of remove method, of class WaitingRequestDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Remove Waiting Request");
        WaitingRequest waitingRequest = new WaitingRequest();

        waitingRequest = waitingRequestDAO.create(waitingRequest);
        assertNotNull(waitingRequest);
        int id = waitingRequest.getId();
        WaitingRequest result = waitingRequestDAO.remove(id);
        assertEquals(waitingRequest, result);

        try {
            WaitingRequest nullResult = waitingRequestDAO.getById(id);
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }

    }
}
