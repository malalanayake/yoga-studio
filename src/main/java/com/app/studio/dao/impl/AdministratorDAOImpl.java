package com.app.studio.dao.impl;

import com.app.studio.dao.AdministratorDAO;
import com.app.studio.model.Administrator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * DAO class for administrator data access
 *
 * @author Yen
 */
@Repository
public class AdministratorDAOImpl implements AdministratorDAO {

    private static final Logger logger = LoggerFactory.getLogger(AdministratorDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Administrator create(Administrator a) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(a);
        if (logger.isDebugEnabled()) {
            logger.debug("Administrator saved successfully, Administrator Details=" + a);
        }
        return a;
    }

    @Override
    public Administrator update(Administrator a) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(a);
        if (logger.isDebugEnabled()) {
            logger.debug("Administrator updated successfully, Administrator Details=" + a);
        }
        return a;
    }

    @Override
    public List<Administrator> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Administrator> adminList = session.createQuery("from Administrator").list();
        if (logger.isDebugEnabled()) {
            logger.debug("Administrator List:");
            for (Administrator a : adminList) {
                logger.debug(a.toString());
            }
        }
        return adminList;
    }

    @Override
    public Administrator getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Administrator a = (Administrator) session.get(Administrator.class, Integer.valueOf(id));
        if (logger.isDebugEnabled()) {
            logger.debug("Administrator loaded successfully, Administrator Details=" + a);
        }
        return a;
    }

    @Override
    public Administrator remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Administrator a = (Administrator) session.load(Administrator.class, Integer.valueOf(id));
        if (a != null) {
            session.delete(a);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Administrator deleted successfully, Administrator Details=" + a);
        }
        return a;
    }

    @Override
    public Administrator getByUserName(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Administrator> adminList = session.getNamedQuery(Administrator.Constants.NAME_QUERY_FIND_BY_USER_NAME)
                .setParameter(Administrator.Constants.PARAM_USER_NAME, userName).list();
        Administrator admin = null;
        if (!adminList.isEmpty()) {
            admin = adminList.get(0);
        }
        return admin;
    }

}
