package com.app.studio.dao;
import com.app.studio.model.Semester;
import java.util.List;


/**
 *SemesterDAO Operations
 * @author jCalles
 */

    
    public interface SemesterDAO {

    /**
     * Create a new Semester
     *
     * @param p
     * @return
     */
    public Semester create(Semester s);

    /**
     * Update existing customer
     *
     * @param p
     * @return
     */
    public Semester update(Semester s);

    /**
     * List all the semesters
     *
     * @return
     */
    public List<Semester> list();

    /**
     * Get Semester by ID
     *
     * @param id
     * @return
     */
    public Semester getById(int id);

    /**
     * Remove Semester
     *
     * @param id
     * @return
     */
    public Semester remove(int id);
}
        
    

