package com.app.studio.dao;

import com.app.studio.model.Semester;
import java.util.List;

/**
 * SemesterDAO Operations
 *
 * @author jCalles
 */
public interface SemesterDAO {

    /**
     * Create a new Semester
     *
     * @param s
     * @return
     */
    public Semester create(Semester s);

    /**
     * Update existing customer
     *
     * @param s
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

    /**
     * Get list of semesters with sign up date equal to or later than today's
     * date.
     *
     * @return
     */
    public List<Semester> getBySignUpDate();

    /**
     * Get list of current and future semesters
     *
     * @return
     */
    public List<Semester> getCurrentAndFutureSemesters();
}
