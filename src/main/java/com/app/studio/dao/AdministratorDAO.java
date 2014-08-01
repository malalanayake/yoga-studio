package com.app.studio.dao;

import com.app.studio.model.Administrator;
import java.util.List;

/**
 * AdministratorDAO operations
 *
 * @author Yen
 */
public interface AdministratorDAO {

    /**
     * Creates a new Administrator record
     *
     * @param a - the administrator to be created
     * @return the created administrator object
     */
    Administrator create(Administrator a);

    /**
     * Updates an Administrator record
     *
     * @param a - the administrator with updated values
     * @return the updated administrator object
     */
    Administrator update(Administrator a);

    /**
     * Returns a list of all Administrator records
     *
     * @return a list of all administrator objects
     */
    List<Administrator> list();

    /**
     * Returns the Administrator record with the specified ID
     *
     * @param id - the Administrator ID
     * @return the administrator object with the specified ID
     */
    Administrator getById(int id);

    /**
     * Deletes the Administrator record with the specified ID
     *
     * @param id - the Administrator ID
     * @return the deleted administrator object
     */
    Administrator remove(int id);

}
