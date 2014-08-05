package com.app.studio.service.impl;

import com.app.studio.dao.FacultyDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import com.app.studio.service.FacultyService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of Faculty Service
 *
 * @author Yen
 */
@Service
public class FacultyServiceImpl implements FacultyService {

    private FacultyDAO facultyDAO;

    @Override
    @Transactional
    public Faculty createNewFaculty(User user) throws RequiredDataNotPresent, RecordAlreadyExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Faculty updateFaculty(Faculty faculty) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Faculty deleteFaculty(Faculty faculty) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Faculty> listAllFaculties(Faculty faculty) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Faculty getFacultyByID(int facultyID) throws RequiredDataNotPresent {
        Faculty faculty = facultyDAO.getById(facultyID);
        if (faculty != null) {
            return faculty;
        } else {
            throw new RequiredDataNotPresent("Primary key not present");
        }
    }

    public FacultyDAO getFacultyDAO() {
        return facultyDAO;
    }

    public void setFacultyDAO(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;
    }
}
