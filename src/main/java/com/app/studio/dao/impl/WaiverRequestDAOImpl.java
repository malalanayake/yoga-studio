package com.app.studio.dao.impl;

import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.model.WaiverRequest;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * WaiverRequestDAO implementation which is doing the data access operation for
 * WaiverRequest
 *
 * @author malalanayake
 */
@Repository
public class WaiverRequestDAOImpl implements WaiverRequestDAO {

    private static final Logger logger = LoggerFactory.getLogger(WaiverRequestDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public WaiverRequest create(WaiverRequest waiverRequest) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(waiverRequest);
        if (logger.isDebugEnabled()) {
            logger.debug("WaiverRequest saved successfully, WaiverRequest Details=" + waiverRequest);
        }
        return waiverRequest;
    }

    @Override
    public WaiverRequest update(WaiverRequest waiverRequest) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(waiverRequest);
        if (logger.isDebugEnabled()) {
            logger.info("WaiverRequest updated successfully, WaiverRequest Details=" + waiverRequest);
        }
        return waiverRequest;
    }

    @Override
    public List<WaiverRequest> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<WaiverRequest> waiverRequestList = session.createQuery("from WaiverRequest").list();
        if (logger.isDebugEnabled()) {
            for (WaiverRequest c : waiverRequestList) {
                logger.info("WaiverRequest List::" + c);
            }
        }
        return waiverRequestList;
    }

    @Override
    public WaiverRequest getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        WaiverRequest waiverRequest = (WaiverRequest) session.load(WaiverRequest.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.info("WaiverRequest loaded successfully, WaiverRequest details=" + waiverRequest);
        }
        return waiverRequest;
    }

    @Override
    public WaiverRequest remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        WaiverRequest waiverRequest = (WaiverRequest) session.load(WaiverRequest.class, new Integer(id));
        if (null != waiverRequest) {
            session.delete(waiverRequest);
        }
        if (logger.isDebugEnabled()) {
            logger.info("WaiverRequest deleted successfully, WaiverRequest details=" + waiverRequest);
        }
        return waiverRequest;
    }

}
