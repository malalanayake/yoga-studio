package com.app.studio.dao;

import com.app.studio.model.User;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public interface UserDAO {

    public User create(User user);

    public User update(User user);

    public List<User> list();

    public User getById(int id);

    public User remove(int id);
}
