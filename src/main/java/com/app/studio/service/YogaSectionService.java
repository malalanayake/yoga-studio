package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.YogaClass;
import java.util.List;

/**
 * Interface which is going to provide the yoga section operations
 *
 * @author malalanayake
 */
public interface YogaSectionService {

    /**
     * Create new section
     *
     * @param yogaClass
     * @param semester
     * @return
     * @throws RequiredDataNotPresent
     * @throws RecordAlreadyExistException
     */
    public Section createNewSection(YogaClass yogaClass, Semester semester, Faculty faculty, Section section) throws RequiredDataNotPresent,
            RecordAlreadyExistException;

    /**
     * Update section
     *
     * @param section
     * @return
     * @throws RequiredDataNotPresent
     */
    public Section updateSection(Section section) throws RequiredDataNotPresent;

    /**
     * Delete section
     *
     * @param section
     * @return
     * @throws RequiredDataNotPresent
     */
    public Section deleteSection(Section section) throws RequiredDataNotPresent;

    /**
     * List all sections
     *
     * @return
     */
    public List<Section> listOfAllSections();
    
    public Section getSectionByID(int sectionID);
}
