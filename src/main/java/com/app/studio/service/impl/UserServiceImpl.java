package com.app.studio.service.impl;

import com.app.studio.dao.UserDAO;
import com.app.studio.model.User;
import com.app.studio.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dmalalanayake
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        return userDAO.getByUserName(userName);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
