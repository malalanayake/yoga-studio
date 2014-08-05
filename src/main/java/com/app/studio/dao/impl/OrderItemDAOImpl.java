package com.app.studio.dao.impl;

import com.app.studio.dao.OrderItemDAO;
import com.app.studio.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ahmadreza
 */
@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public OrderItem create(OrderItem o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(o);
        if (logger.isDebugEnabled()) {
            logger.debug("OrderItem saved successfully, OrderItem Details=" + o);
        }
        return o;
    }

    @Override
    public OrderItem update(OrderItem o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(o);
        if (logger.isDebugEnabled()) {
            logger.debug("OrderItem updated successfully, OrderItem Details=" + o);
        }
        return o;
    }

    @Override
    public OrderItem getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        OrderItem o = (OrderItem) session.load(OrderItem.class, Integer.valueOf(id));
        if (logger.isDebugEnabled()) {
            logger.debug("OrderItem loaded successfully, OrderItem Details=" + o);
        }
        return o;
    }

    @Override
    public OrderItem remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        OrderItem o = (OrderItem) session.load(OrderItem.class, Integer.valueOf(id));
        if (o != null) {
            session.delete(o);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("OrderItem deleted successfully, OrderItem Details=" + o);
        }
        return o;
    }

}
