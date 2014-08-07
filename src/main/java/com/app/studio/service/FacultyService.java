package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
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
     * @param user
     * @return
     * @throws RequiredDataNotPresent
     */
    public Faculty updateFaculty(User user) throws RequiredDataNotPresent;

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
    public List<Faculty> listAllFaculties();

    /**
     * Get faculty by faculty ID
     *
     * @param facultyID
     * @return
     * @throws RequiredDataNotPresent
     */
    public Faculty getFacultyByID(int facultyID) throws RequiredDataNotPresent;
    
    /**
     * Get faculty by username
     *
     * @param username
     * @return
     * @throws RequiredDataNotPresent
     */
    public Faculty getFacultyByUsername(String username) throws RequiredDataNotPresent;

    /**
     * Approves or rejects a waiver request
     *
     * @param waiverRequestID
     * @param isApproved
     * @return
     * @throws RequiredDataNotPresent
     */
    public WaiverRequest submitWaiverResponse(int waiverRequestID, boolean isApproved) throws RequiredDataNotPresent;
}
