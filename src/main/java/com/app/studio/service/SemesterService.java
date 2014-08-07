package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Semester;
import java.util.List;

/**
 * Interface which is going to provide the semester service operations
 *
 * @author malalanayake
 */
public interface SemesterService {

    /**
     * Create new Semester
     *
     * @param semester
     * @return
     * @throws RequiredDataNotPresent
     * @throws RecordAlreadyExistException
     */
    public Semester createNewSemester(Semester semester) throws RequiredDataNotPresent,
            RecordAlreadyExistException;

    /**
     * Update Semester
     *
     * @return
     * @throws RequiredDataNotPresent
     */
    public Semester updateSemeter(Semester semester) throws RequiredDataNotPresent;

    /**
     * Delete Semester
     *
     * @return
     * @throws RequiredDataNotPresent
     */
    public Semester deleteSemester(Semester semester) throws RequiredDataNotPresent;

    /**
     * List all semesters
     *
     * @return
     */
    public List<Semester> listOfAllSemesters();
    
    public Semester getSemeterByID(int id);
}
