package com.app.studio.dao.impl;

import com.app.studio.dao.EnrolledSectionDAO;
import com.app.studio.model.EnrolledSection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * EnrolledSectionDAO Implementation
 *
 * @author malalanayake
 */
@Repository
public class EnrolledSectionDAOImpl implements EnrolledSectionDAO {

    private static final Logger logger = LoggerFactory.getLogger(EnrolledSectionDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public EnrolledSection create(EnrolledSection enrolledSection) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(enrolledSection);
        if (logger.isDebugEnabled()) {
            logger.debug("EnrolledSection saved successfully, EnrolledSection Details=" + enrolledSection);
        }
        return enrolledSection;
    }

    @Override
    public EnrolledSection update(EnrolledSection enrolledSection) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(enrolledSection);
        if (logger.isDebugEnabled()) {
            logger.debug("EnrolledSection updated successfully, EnrolledSection Details=" + enrolledSection);
        }
        return enrolledSection;
    }

    @Override
    public List<EnrolledSection> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<EnrolledSection> enrolledList = session.createQuery("from EnrolledSection").list();
        if (logger.isDebugEnabled()) {
            for (EnrolledSection c : enrolledList) {
                logger.debug("EnrolledSection List::" + c);
            }
        }
        return enrolledList;
    }

    @Override
    public EnrolledSection getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EnrolledSection enrolledSection = (EnrolledSection) session.load(EnrolledSection.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.debug("EnrolledSection loaded successfully, EnrolledSection details=" + enrolledSection);
        }
        return enrolledSection;
    }

    @Override
    public EnrolledSection remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EnrolledSection enrolledSection = (EnrolledSection) session.load(EnrolledSection.class, new Integer(id));
        if (null != enrolledSection) {
            session.delete(enrolledSection);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("EnrolledSection deleted successfully, EnrolledSection details=" + enrolledSection);
        }
        return enrolledSection;
    }

}
