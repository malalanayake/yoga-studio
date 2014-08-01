package com.app.studio.dao.impl;

import com.app.studio.dao.UserDAO;
import com.app.studio.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * User DAO which is doing the data access operation for User
 *
 * @author malalanayake
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public User create(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        if (logger.isDebugEnabled()) {
            logger.debug("User saved successfully, User Details=" + user);
        }
        return user;
    }

    @Override
    public User update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        if (logger.isDebugEnabled()) {
            logger.debug("User update successfully, User Details=" + user);
        }
        return user;
    }

    @Override
    public List<User> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        if (logger.isDebugEnabled()) {
            for (User c : userList) {
                logger.debug("User List::" + c);
            }
        }
        return userList;
    }

    @Override
    public User getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.debug("User loaded successfully, User details=" + user);
        }
        return user;
    }

    @Override
    public User remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (null != user) {
            session.delete(user);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("User deleted successfully, User details=" + user);
        }
        return user;
    }

}
