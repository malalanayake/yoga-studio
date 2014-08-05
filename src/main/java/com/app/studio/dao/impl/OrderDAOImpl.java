package com.app.studio.dao.impl;

import com.app.studio.dao.OrderDAO;
import com.app.studio.model.Order;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * DAO class for order data access
 *
 * @author Yen
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Order create(Order o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(o);
        if (logger.isDebugEnabled()) {
            logger.debug("Order saved successfully, Order Details=" + o);
        }
        return o;
    }

    @Override
    public Order update(Order o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(o);
        if (logger.isDebugEnabled()) {
            logger.debug("Order updated successfully, Order Details=" + o);
        }
        return o;
    }

    @Override
    public List<Order> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> orderList = session.createQuery("from Order").list();
        if (logger.isDebugEnabled()) {
            logger.debug("Order List:");
            for (Order o : orderList) {
                logger.debug(o.toString());
            }
        }
        return orderList;
    }

    @Override
    public Order getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order o = (Order) session.load(Order.class, Integer.valueOf(id));
        if (logger.isDebugEnabled()) {
            logger.debug("Order loaded successfully, Order Details=" + o);
        }
        return o;
    }

    @Override
    public Order remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order o = (Order) session.load(Order.class, Integer.valueOf(id));
        if (o != null) {
            session.delete(o);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Order deleted successfully, Order Details=" + o);
        }
        return o;
    }

}
