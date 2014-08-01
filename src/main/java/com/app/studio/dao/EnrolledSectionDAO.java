package com.app.studio.dao;

import com.app.studio.model.EnrolledSection;
import java.util.List;

/**
 * Interface for provide the EnrolledSection Operation
 *
 * @author malalanayake
 */
public interface EnrolledSectionDAO {

    /**
     * Create a new Enrolled Section
     *
     * @param enrolledSection
     * @return
     */
    public EnrolledSection create(EnrolledSection enrolledSection);

    /**
     * Update existing Enrolled Section
     *
     * @param enrolledSection
     * @return
     */
    public EnrolledSection update(EnrolledSection enrolledSection);

    /**
     * List all Enrolled Section
     *
     * @return
     */
    public List<EnrolledSection> list();

    /**
     * Get EnrolledSection by ID
     *
     * @param id
     * @return
     */
    public EnrolledSection getById(int id);

    /**
     * Remove Enrolled Section
     *
     * @param id
     * @return
     */
    public EnrolledSection remove(int id);
}
