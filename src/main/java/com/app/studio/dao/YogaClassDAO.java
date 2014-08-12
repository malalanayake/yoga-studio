package com.app.studio.dao;

import com.app.studio.model.YogaClass;
import java.util.List;

/**
 * This is the interface which one is providing operations for Yoga Class DAO
 *
 * @author jCalles
 */
public interface YogaClassDAO {

    /**
     * Creates a new YogaClass record
     *
     * @param yogaclass
     * @return the created yoga class object
     */
    public YogaClass create(YogaClass yogaclass);

    /**
     * Updates an YogaClass
     *
     * @param yogaclass - the yoga class with updated values
     * @return the updated yoga class object
     */
    public YogaClass update(YogaClass yogaclass);

    /**
     * Returns a list of all Yoga Class records
     *
     * @return a list of all yoga class objects
     */
    public List<YogaClass> list();

    /**
     * Returns the yoga class record with the specified ID
     *
     * @param id - the Yoga Class ID
     * @return the Yoga Class object with the specified ID
     */
    public YogaClass getById(int id);

    /**
     * Deletes the Yoga Class record with the specified ID
     *
     * @param id - the Yoga Class ID
     * @return the deleted Yoga Class object
     */
    public YogaClass remove(int id);

    /**
     * Get yoga class which is contain the given name
     *
     * @param name
     * @return
     */
    public YogaClass getByName(String name);
}
