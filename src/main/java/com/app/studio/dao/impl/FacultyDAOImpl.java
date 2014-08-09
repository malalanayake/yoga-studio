package com.app.studio.dao.impl;

import com.app.studio.dao.FacultyDAO;
import com.app.studio.model.Faculty;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Faculty DAO which is doing the data access operation for Faculty
 *
 * @author malalanayake
 */
@Repository
public class FacultyDAOImpl implements FacultyDAO {

    private static final Logger logger = LoggerFactory.getLogger(FacultyDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Faculty create(Faculty faculty) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(faculty);
        if (logger.isDebugEnabled()) {
            logger.debug("Faculty saved successfully, Faclty Details=" + faculty);
        }
        return faculty;
    }

    @Override
    public Faculty update(Faculty faculty) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(faculty);
        if (logger.isDebugEnabled()) {
            logger.debug("Faculty update successfully, Faculty Details=" + faculty);
        }
        return faculty;
    }

    @Override
    public List<Faculty> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Faculty> facultyList = session.createQuery("from Faculty").list();
        if (logger.isDebugEnabled()) {
            for (Faculty c : facultyList) {
                logger.debug("Faculty List::" + c);
            }
        }
        return facultyList;
    }

    @Override
    public Faculty getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Faculty faculty = (Faculty) session.get(Faculty.class, new Integer(id));
        if (logger.isDebugEnabled()) {
            logger.debug("Faculty loaded successfully, Faculty details=" + faculty);
        }
        return faculty;
    }

    @Override
    public Faculty remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Faculty faculty = (Faculty) session.load(Faculty.class, new Integer(id));
        if (null != faculty) {
            session.delete(faculty);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Faculty deleted successfully, Faculty details=" + faculty);
        }
        return faculty;
    }

    @Override
    public Faculty getByUserName(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Faculty> facultyList = session.getNamedQuery(Faculty.Constants.NAME_QUERY_FIND_BY_USER_NAME)
                .setParameter(Faculty.Constants.PARAM_USER_NAME, userName).list();
        Faculty faculty = null;
        if (!facultyList.isEmpty()) {
            faculty = facultyList.get(0);
        }
        return faculty;
    }

    @Override
    public Faculty getNextAdvisor() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Faculty> facultyList = session.getNamedQuery(Faculty.Constants.NAME_QUERY_FIND_NEXT_ADVISOR).list();
        Faculty faculty = null;
        if (!facultyList.isEmpty()) {
            faculty = facultyList.get(0);
        }
        return faculty;
    }
}
