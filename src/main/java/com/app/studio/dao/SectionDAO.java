package com.app.studio.dao;

import com.app.studio.model.Section;
import java.util.List;

/**
 * SectionDAO operations
 *
 * @author Yen
 */
public interface SectionDAO {

    /**
     * Creates a new Section record
     *
     * @param s - the section to be created
     * @return the created section object
     */
    Section create(Section s);

    /**
     * Updates an Section record
     *
     * @param s - the section with updated values
     * @return the updated section object
     */
    Section update(Section s);

    /**
     * Returns a list of all Section records
     *
     * @return a list of all section objects
     */
    List<Section> list();

    /**
     * Returns the Section record with the specified ID
     *
     * @param id - the Section ID
     * @return the section object with the specified ID
     */
    Section getById(int id);

    /**
     * Deletes the Section record with the specified ID
     *
     * @param id - the Section ID
     * @return the deleted section object
     */
    Section remove(int id);

}
