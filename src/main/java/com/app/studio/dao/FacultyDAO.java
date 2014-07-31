package com.app.studio.dao;

import com.app.studio.model.Faculty;
import java.util.List;

/**
 * Interface for the faculty operations
 *
 * @author malalanayake
 */
public interface FacultyDAO {

    public Faculty create(Faculty faculty);

    public Faculty update(Faculty faculty);

    public List<Faculty> list();

    public Faculty getById(int id);

    public Faculty remove(int id);
}
