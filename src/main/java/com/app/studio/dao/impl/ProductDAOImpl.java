package com.app.studio.dao.impl;

import com.app.studio.dao.ProductDAO;
import com.app.studio.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aTabibi
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Product create(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        if (logger.isDebugEnabled()) {
            logger.debug("Product saved successfully, Product Details=" + p);
        }
        return p;
    }

    @Override
    public Product update(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        if (logger.isDebugEnabled()) {
            logger.debug("Product saved successfully, Product Details=" + p);
        }
        return p;
    }

    @Override
    public Product getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product p = (Product) session.load(Product.class, Integer.valueOf(id));
        if (logger.isDebugEnabled()) {
            logger.debug("Product loaded successfully, Product Details=" + p);
        }
        return p;
    }

    @Override
    public Product remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product p = (Product) session.load(Product.class, Integer.valueOf(id));
        if (p != null) {
            session.delete(p);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Product deleted successfully, Product Details=" + p);
        }
        return p;
    }

}
