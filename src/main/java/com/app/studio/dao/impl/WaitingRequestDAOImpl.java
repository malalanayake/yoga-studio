package com.app.studio.dao.impl;

import com.app.studio.dao.WaitingRequestDAO;
import com.app.studio.model.WaitingRequest;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * ShoppingCartDAO implementation
 *
 * @author malalanayake
 */
@Repository
public class WaitingRequestDAOImpl implements WaitingRequestDAO {

    private static final Logger logger = LoggerFactory.getLogger(WaitingRequestDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public WaitingRequest create(WaitingRequest waitingRequest) {

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(waitingRequest);
        if (logger.isDebugEnabled()) {
            logger.debug("Waiting Request saved successfully, Waiting Request Details=" + waitingRequest);
        }
        return waitingRequest;
    }

    @Override
    public WaitingRequest update(WaitingRequest waitingRequest) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(waitingRequest);
        if (logger.isDebugEnabled()) {
            logger.debug("Waiting Request updated successfully, Waiting Request Details=" + waitingRequest);
        }
        return waitingRequest;
    }

    @Override
    public WaitingRequest getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        WaitingRequest waitingRequest = (WaitingRequest) session.load(WaitingRequest.class, Integer.valueOf(id));
        if (logger.isDebugEnabled()) {
            logger.debug("WaitingRequest loaded successfully, WaitingRequest Details=" + waitingRequest);
        }
        return waitingRequest;
    }

    @Override
    public WaitingRequest remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        WaitingRequest waitingRequest = (WaitingRequest) session.load(WaitingRequest.class, Integer.valueOf(id));
        if (waitingRequest != null) {
            session.delete(waitingRequest);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Waiting Request deleted successfully, Waiting Request Details=" + waitingRequest);
        }
        return waitingRequest;
    }

    @Override
    public List<WaitingRequest> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<WaitingRequest> waitingList = session.createQuery("from WaitingRequest").list();
        if (logger.isDebugEnabled()) {
            for (WaitingRequest waitingRequest : waitingList) {
                logger.debug("WaitingRequest List::" + waitingRequest);
            }
        }
        return waitingList;
    }

}
