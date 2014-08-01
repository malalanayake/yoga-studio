package com.app.studio.dao.impl;

import com.app.studio.dao.SemesterDAO;
import com.app.studio.model.Semester;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * DAO class which is providing the semester data access
 *
 * @author jCalles
 */
@Repository
public class SemesterDAOImpl implements SemesterDAO{

    private static final Logger logger = LoggerFactory.getLogger(SemesterDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Semester create(Semester semester) {
    Session session = this.sessionFactory.getCurrentSession();
        session.persist(semester);
        if (logger.isDebugEnabled()) {
            logger.debug("Semester saved successfully, Semester Details=" + semester);
        }
        return semester;
    }

    @Override
    public Semester update(Semester semester) {
        
        Session session = this.sessionFactory.getCurrentSession();
        session.update(semester);
        if (logger.isDebugEnabled()) {
            logger.debug("Semester updated successfully, Semester Details=" + semester);
        }
        return semester;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Semester> list() {
           Session session = this.sessionFactory.getCurrentSession();
        List<Semester> semesterList = session.createQuery("from Semester").list();
        if (logger.isDebugEnabled()) {
            for (Semester semester : semesterList) {
                logger.debug("Semester List::" + semester);
            }
        }
        return semesterList;
        
    }

    @Override
    public Semester getById(int id) {
       Session session = this.sessionFactory.getCurrentSession();
        Semester semester = (Semester) session.load(Semester.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.debug("Semester loaded successfully, Semester details=" + semester);
        }
        return semester;
    }

    @Override
    public Semester remove(int id) {
             Session session = this.sessionFactory.getCurrentSession();
        Semester semester = (Semester) session.load(Semester.class, new Integer(id));
        if (null != semester) {
            session.delete(semester);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Semester deleted successfully, Semester details=" + semester);
        }
        return semester;
    }

}

