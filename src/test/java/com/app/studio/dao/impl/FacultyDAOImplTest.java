package com.app.studio.dao.impl;

import com.app.studio.dao.FacultyDAO;
import com.app.studio.model.Faculty;
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
public class FacultyDAOImplTest {

    @Autowired
    private FacultyDAO facultyDAO;

    public FacultyDAOImplTest() {
    }

    /**
     * Test of create method, of class FacultyDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Faculty faculty = new Faculty();
        Faculty result = facultyDAO.create(faculty);
        assertNotNull(result.getId());
    }

}
