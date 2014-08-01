package com.app.studio.dao.impl;

import com.app.studio.dao.SectionDAO;
import com.app.studio.model.Section;
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
public class SectionDAOImplTest {

    @Autowired
    private SectionDAO sectionDAO;

    /**
     * Test of create method, of class SectionDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Section s = new Section();
        Section result = sectionDAO.create(s);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    /**
     * Test of update method, of class SectionDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Section s = sectionDAO.create(new Section());
        assertNotNull(s.getId());

        int newId = s.getId() + 1;
        s.setId(newId);
        Section result = sectionDAO.update(s);
        assertEquals(newId, result.getId());
    }

    /**
     * Test of getById method, of class SectionDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Section s = sectionDAO.create(new Section());
        assertNotNull(s.getId());

        int id = s.getId();
        Section result = sectionDAO.getById(id);
        assertEquals(s, result);
    }

    /**
     * Test of remove method, of class SectionDAOImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Section s = sectionDAO.create(new Section());
        assertNotNull(s.getId());

        int id = s.getId();
        Section result = sectionDAO.remove(id);
        assertEquals(s, result);

        try {
            Section nullResult = sectionDAO.getById(id);
            assertNull(nullResult);
        } catch (Exception e) {
            assertEquals(org.hibernate.ObjectNotFoundException.class, e.getClass());
        }
    }

}
