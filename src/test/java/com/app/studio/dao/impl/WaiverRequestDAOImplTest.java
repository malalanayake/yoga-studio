package com.app.studio.dao.impl;

import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.model.WaiverRequest;
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
public class WaiverRequestDAOImplTest {

    @Autowired
    private WaiverRequestDAO waiverRequestDAO;

    public WaiverRequestDAOImplTest() {
    }

    /**
     * Test of create method, of class WaiverRequestDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        WaiverRequest waiverRequest = new WaiverRequest();
        waiverRequest.setStatus("HOLD");
        WaiverRequest result = waiverRequestDAO.create(waiverRequest);
        assertNotNull(result.getId());
    }

}
