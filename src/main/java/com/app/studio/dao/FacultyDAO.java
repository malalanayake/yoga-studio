package com.app.studio.dao;

import com.app.studio.model.Faculty;
import java.util.List;

/**
 * Interface for the faculty operations
 *
 * @author malalanayake
 */
public interface FacultyDAO {

    /**
     * Create a new Faculty
     *
     * @param faculty
     * @return
     */
    public Faculty create(Faculty faculty);

    /**
     * Update existing faculty
     *
     * @param faculty
     * @return
     */
    public Faculty update(Faculty faculty);

    /**
     * List all Faculty
     *
     * @return
     */
    public List<Faculty> list();

    /**
     * Get Faculty by ID
     *
     * @param id
     * @return
     */
    public Faculty getById(int id);

    /**
     * Remove Faculty
     *
     * @param id
     * @return
     */
    public Faculty remove(int id);
    
    public Faculty getByUserName(String userName);
}
