package com.app.studio.dao.impl;

import com.app.studio.dao.ShoppingCartDAO;
import com.app.studio.model.ShoppingCart;
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
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(shoppingCart);
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCart saved successfully, ShoppingCart Details=" + shoppingCart);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(shoppingCart);
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCart updated successfully, ShoppingCart Details=" + shoppingCart);
        }
        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ShoppingCart> shoppingCartList = session.createQuery("from ShoppingCart").list();
        if (logger.isDebugEnabled()) {
            for (ShoppingCart c : shoppingCartList) {
                logger.debug("ShoppingCart List::" + c);
            }
        }
        return shoppingCartList;
    }

    @Override
    public ShoppingCart getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.load(ShoppingCart.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCart loaded successfully, ShoppingCart details=" + shoppingCart);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart remove(int id) {
    Session session = this.sessionFactory.getCurrentSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.load(ShoppingCart.class, new Integer(id));
        if (null != shoppingCart) {
            session.delete(shoppingCart);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCart deleted successfully, ShoppingCart details=" + shoppingCart);
        }
        return shoppingCart;    
    }

}
