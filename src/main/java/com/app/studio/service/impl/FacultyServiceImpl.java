package com.app.studio.service.impl;

import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.security.Roles;
import com.app.studio.service.FacultyService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private WaiverRequestDAO waiverRequestDAO;
    private UserDAO userDAO;

    @Override
    @Transactional
    public Faculty createNewFaculty(User user) throws RequiredDataNotPresent, RecordAlreadyExistException {
        Faculty faculty = null;
        if (!user.getFirstName().equals("") && !user.getLastName().equals("") && !user.getUsername().equals("") && !user.getPassword().equals("")
                && !user.getSequrityQuestion().equals("") && !user.getAnswer().equals("")) {
            if (userDAO.getByUserName(user.getUsername()) == null) {
                user.addRole(Roles.ROLE_FACULTY);
                user = userDAO.create(user);
                faculty = new Faculty(user);
                faculty = this.facultyDAO.create(faculty);
            } else {
                throw new RecordAlreadyExistException("Username already exists");
            }
        } else {
            throw new RequiredDataNotPresent("You have to complete all the fields");
        }

        return faculty;
    }

    @Override
    @Transactional
    public Faculty updateFaculty(User user) throws RequiredDataNotPresent {
        User u = user;
        Faculty faculty = null;
        if (!u.getFirstName().equals("") && !u.getLastName().equals("") && !u.getUsername().equals("") && !u.getPassword().equals("")
                && !u.getSequrityQuestion().equals("") && !u.getAnswer().equals("")) {
            u.addRole(Roles.ROLE_FACULTY);
            u = userDAO.update(u);
            faculty = this.facultyDAO.getByUserName(u.getUsername());
        } else {
            throw new RequiredDataNotPresent("You have to complete all the fields");
        }

        return faculty;
    }

    @Override
    @Transactional
    public Faculty deleteFaculty(Faculty faculty) throws RequiredDataNotPresent {
        User u = faculty.getUser();
        if (faculty.getId() > 0 && u.getId() > 0) {
            faculty = this.facultyDAO.remove(faculty.getId());
            u = userDAO.remove(u.getId());
        } else {
            throw new RequiredDataNotPresent("You have to select the faculty to delete");
        }

        return faculty;
    }

    @Override
    @Transactional
    public List<Faculty> listAllFaculties() {
        return facultyDAO.list();
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

    @Override
    @Transactional
    public Faculty getFacultyByUsername(String username) throws RequiredDataNotPresent {
        Faculty faculty = facultyDAO.getByUserName(username);
        if (faculty != null) {
            return faculty;
        } else {
            throw new RequiredDataNotPresent("Primary key not present");
        }
    }

    @Override
    @Transactional
    public WaiverRequest submitWaiverResponse(int waiverRequestID, boolean isApproved) throws RequiredDataNotPresent {
        WaiverRequest waiver = waiverRequestDAO.getById(waiverRequestID);
        if (waiver != null) {
            if (isApproved) {
                waiver.setStatus(WaiverRequest.Constants.STATUS_APPROVED);
            } else {
                waiver.setStatus(WaiverRequest.Constants.STATUS_REJECTED);
            }
            waiver = waiverRequestDAO.update(waiver);
            return waiver;
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

    public WaiverRequestDAO getWaiverRequestDAO() {
        return waiverRequestDAO;
    }

    public void setWaiverRequestDAO(WaiverRequestDAO waiverRequestDAO) {
        this.waiverRequestDAO = waiverRequestDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
