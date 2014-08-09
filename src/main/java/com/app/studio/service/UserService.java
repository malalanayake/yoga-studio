package com.app.studio.service;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.User;

/**
 *
 * @author dmalalanayake
 */
public interface UserService {

    public User getUserByUserName(String userName);
    
    public User updateUser (User user) throws RequiredDataNotPresent;
    
}

