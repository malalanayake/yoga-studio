package com.app.studio.dao.impl;

import com.app.studio.dao.SemesterDAO;
import com.app.studio.model.Semester;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
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
public class SemesterDAOImplTest {

    @Autowired
    private SemesterDAO semesterDAO;

    public SemesterDAOImplTest() {
    }

    /**
     * Test of create method, of class SemesterDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("addSemester");
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        Semester expectSem = semesterDAO.create(sem);
        assertNotNull(expectSem.getId());
    }

    /**
     * Test of update method, of class SemesterDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("updateSemester");
        Semester sem = new Semester();
        sem.setSignUpDate("2014-03-03");
        Semester expectSem;
        expectSem = semesterDAO.update(sem);
        assertNotNull(expectSem.getId());

    }

}
