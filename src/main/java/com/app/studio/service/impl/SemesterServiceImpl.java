/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.studio.service.impl;

import com.app.studio.common.DateUtils;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Semester;
import com.app.studio.service.SemesterService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dmalalanayake
 */
@Service
public class SemesterServiceImpl implements SemesterService {

    private SemesterDAO semesterDAO;

    @Override
    @Transactional
    public Semester createNewSemester(Semester semester) throws RequiredDataNotPresent, RecordAlreadyExistException {
        Semester sem = null;
        if (!semester.getEnddate().equals("") && !semester.getSignUpDate().equals("")
                && !semester.getStartdate().equals("")) {
            Date endDate;
            Date startDate;
            Date signUpDate;
            try {
                endDate = DateUtils.parse(semester.getEnddate());
                startDate = DateUtils.parse(semester.getStartdate());
                signUpDate = DateUtils.parse(semester.getSignUpDate());
            } catch (ParseException ex) {
                throw new RequiredDataNotPresent("You have enterd different values for date");
            }

            if (startDate.before(endDate) && signUpDate.before(startDate)) {
                sem = semesterDAO.create(semester);
            } else {
                throw new RequiredDataNotPresent("Error in either start date is not before end date or signup date is not before start date");
            }
        } else {
            throw new RequiredDataNotPresent("Required data not present to create semester");
        }
        return sem;
    }

    @Override
    @Transactional
    public Semester updateSemeter(Semester semester) throws RequiredDataNotPresent {
        Semester sem = null;
        if (!semester.getEnddate().equals("") && !semester.getSignUpDate().equals("")
                && !semester.getStartdate().equals("")) {
            if (semester.getId() > 0) {
                Date endDate;
                Date startDate;
                Date signUpDate;
                try {
                    endDate = DateUtils.parse(semester.getEnddate());
                    startDate = DateUtils.parse(semester.getStartdate());
                    signUpDate = DateUtils.parse(semester.getSignUpDate());
                } catch (ParseException ex) {
                    throw new RequiredDataNotPresent("You have enterd different values for date");
                }

                if (startDate.before(endDate) && signUpDate.before(startDate)) {
                    sem = semesterDAO.update(semester);
                } else {
                    throw new RequiredDataNotPresent("Error in either start date is not before end date or signup date is not before start date");
                }
            } else {
                throw new RequiredDataNotPresent("Primery key not present");
            }
        } else {
            throw new RequiredDataNotPresent("Required data not presenet to create semester");
        }
        return sem;
    }

    @Override
    @Transactional
    public Semester deleteSemester(Semester semester) throws RequiredDataNotPresent {
        Semester sem = null;
        if (semester.getId() > 0) {
            sem = semesterDAO.remove(semester.getId());
        } else {
            throw new RequiredDataNotPresent("Primery key not present");
        }
        return sem;
    }

    @Override
    @Transactional
    public List<Semester> listOfAllSemesters() {
        return semesterDAO.list();
    }

    @Override
    @Transactional
    public List<Semester> listOfCurrentAndFutureSemesters() {
        return semesterDAO.getCurrentAndFutureSemesters();
    }

    public SemesterDAO getSemesterDAO() {
        return semesterDAO;
    }

    public void setSemesterDAO(SemesterDAO semesterDAO) {
        this.semesterDAO = semesterDAO;
    }

    @Override
    @Transactional
    public Semester getSemeterByID(int id) {
        return this.semesterDAO.getById(id);
    }

}
