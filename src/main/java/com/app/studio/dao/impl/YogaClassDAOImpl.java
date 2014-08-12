package com.app.studio.dao.impl;

import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.YogaClass;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * YogaClass DAO which is doing the data access operation for Yoga Class
 *
 * @author jCalles
 */
@Repository
public class YogaClassDAOImpl implements YogaClassDAO {

    private static final Logger logger = LoggerFactory.getLogger(YogaClassDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;

    }

    @Override
    public YogaClass create(YogaClass yogaclass) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(yogaclass);
        if (logger.isDebugEnabled()) {
            logger.debug("Yoga Class saved successfully, Yoga Class Details=" + yogaclass);
        }
        return yogaclass;
    }

    @Override
    public YogaClass update(YogaClass yogaclass) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(yogaclass);
        if (logger.isDebugEnabled()) {
            logger.debug("Yoga class update successfully, Yoga Class Details=" + yogaclass);
        }
        return yogaclass;
    }

    @Override
    public List<YogaClass> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<YogaClass> yogaclassList = session.createQuery("from YogaClass").list();
        if (logger.isDebugEnabled()) {
            for (YogaClass yogaclass : yogaclassList) {
                logger.debug("Yoga Class List::" + yogaclass);
            }
        }
        return yogaclassList;
    }

    @Override
    public YogaClass getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        YogaClass yogaclass = (YogaClass) session.get(YogaClass.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.debug("Yoga Class loaded successfully, Yoga Class details=" + yogaclass);
        }
        return yogaclass;

    }

    @Override
    public YogaClass remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        YogaClass yogaclass = (YogaClass) session.load(YogaClass.class, new Integer(id));
        if (null != yogaclass) {
            session.delete(yogaclass);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Yoga Class deleted successfully, Yoga Class details=" + yogaclass);
        }
        return yogaclass;

    }

    @Override
    public YogaClass getByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        List<YogaClass> userList = null;
        userList = session.getNamedQuery(YogaClass.Constants.NAME_QUERY_FIND_BY_NAME)
                .setParameter(YogaClass.Constants.PARAM_NAME, name).list();
        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
