package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.YogaClass;
import java.util.List;

/**
 * Interface which is going to provide the yoga service operations
 *
 * @author malalanayake
 */
public interface YogaClassService {

    /**
     * Create yoga class
     *
     * @param yogaClass
     * @return
     * @throws RequiredDataNotPresent
     * @throws RecordAlreadyExistException
     */
    public YogaClass createYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent,
            RecordAlreadyExistException;

    /**
     * Update yoga class
     *
     * @param yogaClass
     * @return
     * @throws RequiredDataNotPresent
     */
    public YogaClass updateYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent;

    /**
     * Delete given yoga class
     *
     * @param yogaClass
     * @return
     * @throws RequiredDataNotPresent
     */
    public YogaClass deleteYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent;

    /**
     * List all yoga classes
     *
     * @return
     */
    public List<YogaClass> listOfYogaClasses();
}
