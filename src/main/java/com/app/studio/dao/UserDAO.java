package com.app.studio.dao;

import com.app.studio.model.User;
import java.util.List;

/**
 *
 * @author malalanayake
 */
public interface UserDAO {

    /**
     * Create new User
     *
     * @param user
     * @return
     */
    public User create(User user);

    /**
     * Update existing User
     *
     * @param user
     * @return
     */
    public User update(User user);

    /**
     * List all users
     *
     * @return
     */
    public List<User> list();

    /**
     * List user by ID
     *
     * @param id
     * @return
     */
    public User getById(int id);

    /**
     * Remove user
     *
     * @param id
     * @return
     */
    public User remove(int id);

    /**
     * Get the user by using username
     *
     * @param userName
     * @return
     */
    public User getByUserName(String userName);
}
