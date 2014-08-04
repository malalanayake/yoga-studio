package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import java.util.List;

/**
 * Interface which is going to provide the faculty service operations
 *
 * @author malalanayake
 */
public interface FacultyService {

    /**
     * Create New Faculty
     *
     * @param user
     * @return
     * @throws RequiredDataNotPresent
     * @throws RecordAlreadyExistException
     */
    public Faculty createNewFaculty(User user) throws RequiredDataNotPresent,
            RecordAlreadyExistException;

    /**
     * Update Faculty
     *
     * @param faculty
     * @return
     * @throws RequiredDataNotPresent
     */
    public Faculty updateFaculty(Faculty faculty) throws RequiredDataNotPresent;

    /**
     * Delete Faculty
     *
     * @param faculty
     * @return
     * @throws RequiredDataNotPresent
     */
    public Faculty deleteFaculty(Faculty faculty) throws RequiredDataNotPresent;

    /**
     * List all faculties
     *
     * @param faculty
     * @return
     */
    public List<Faculty> listAllFaculties(Faculty faculty);
}
