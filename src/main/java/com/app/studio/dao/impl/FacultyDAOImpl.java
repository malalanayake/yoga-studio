package com.app.studio.dao.impl;

import com.app.studio.dao.FacultyDAO;
import com.app.studio.model.Faculty;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Faculty DAO which is doing the data access operation for Faculty
 *
 * @author malalanayake
 */
@Repository
public class FacultyDAOImpl implements FacultyDAO {

    private static final Logger logger = LoggerFactory.getLogger(FacultyDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Faculty create(Faculty faculty) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(faculty);
        if (logger.isDebugEnabled()) {
            logger.debug("Faculty saved successfully, Faclty Details=" + faculty);
        }
        return faculty;
    }

    @Override
    public Faculty update(Faculty faculty) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(faculty);
        if (logger.isDebugEnabled()) {
            logger.debug("Faculty update successfully, Faculty Details=" + faculty);
        }
        return faculty;
    }

    @Override
    public List<Faculty> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Faculty> facultyList = session.createQuery("from Faculty").list();
        if (logger.isDebugEnabled()) {
            for (Faculty c : facultyList) {
                logger.debug("Faculty List::" + c);
            }
        }
        return facultyList;
    }

    @Override
    public Faculty getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Faculty faculty = (Faculty) session.load(Faculty.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.info("Faculty loaded successfully, Faculty details=" + faculty);
        }
        return faculty;
    }

    @Override
    public Faculty remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Faculty faculty = (Faculty) session.load(Faculty.class, new Integer(id));
        if (null != faculty) {
            session.delete(faculty);
        }
        if (logger.isDebugEnabled()) {
            logger.info("Faculty deleted successfully, Faculty details=" + faculty);
        }
        return faculty;
    }

}
