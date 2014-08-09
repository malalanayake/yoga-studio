package com.app.studio.service.impl;

import com.app.studio.dao.UserDAO;
import com.app.studio.exception.RequiredDataNotPresent;
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

    @Override
    @Transactional
    public User updateUser(User user) throws RequiredDataNotPresent {
        if (!user.getFirstName().equals("") && !user.getLastName().equals("") &&  !user.getPassword().equals("")
                && !user.getSequrityQuestion().equals("") && !user.getAnswer().equals(""))
        return userDAO.update(user);
        else
        {
           throw new RequiredDataNotPresent("You have to complete all the fields"); 
        }
    }
}

 