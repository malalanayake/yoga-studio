package com.app.studio.dao.impl;

import com.app.studio.dao.ShoppingCartItemDAO;
import com.app.studio.model.ShoppingCartItem;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * DAO class for shopping cart item data access
 *
 * @author Yen
 */
@Repository
public class ShoppingCartItemDAOImpl implements ShoppingCartItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartItemDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public ShoppingCartItem create(ShoppingCartItem s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(s);
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCartItem saved successfully, ShoppingCartItem Details=" + s);
        }
        return s;
    }

    @Override
    public ShoppingCartItem update(ShoppingCartItem s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(s);
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCartItem updated successfully, ShoppingCartItem Details=" + s);
        }
        return s;
    }

    @Override
    public List<ShoppingCartItem> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ShoppingCartItem> itemList = session.createQuery("from ShoppingCartItem").list();
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCartItem List:");
            for (ShoppingCartItem s : itemList) {
                logger.debug(s.toString());
            }
        }
        return itemList;
    }

    @Override
    public ShoppingCartItem getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ShoppingCartItem s = (ShoppingCartItem) session.get(ShoppingCartItem.class, Integer.valueOf(id));
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCartItem loaded successfully, ShoppingCartItem Details=" + s);
        }
        return s;
    }

    @Override
    public ShoppingCartItem remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ShoppingCartItem s = (ShoppingCartItem) session.load(ShoppingCartItem.class, Integer.valueOf(id));
        if (s != null) {
            session.delete(s);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("ShoppingCartItem deleted successfully, ShoppingCartItem Details=" + s);
        }
        return s;
    }

}
