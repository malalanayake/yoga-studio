package com.app.studio.service.impl;

import com.app.studio.dao.SectionDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Faculty;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.YogaClass;
import com.app.studio.service.YogaSectionService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author malalanayake
 */
@Service
public class YogaSectionServiceImpl implements YogaSectionService {
    
    private SectionDAO sectionDAO;
    
    public SectionDAO getSectionDAO() {
        return sectionDAO;
    }
    
    public void setSectionDAO(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }
    
    @Override
    @Transactional
    public Section createNewSection(YogaClass yogaClass, Semester semester, Faculty faculty, Section section) throws RequiredDataNotPresent, RecordAlreadyExistException {
        
        Section sectionToBeCreated = null;
        if ((yogaClass.getId() > 0) && (semester.getId() > 0) && (faculty.getId() > 0)
                && !section.getLocation().equals("") && !section.getSchedule().equals("")
                && (section.getMaxStudents() > 0)) {
            if (sectionDAO.getByLocationNameAndScedule(yogaClass.getId(), section.getLocation(), section.getSchedule()) != null) {
                sectionToBeCreated = new Section(semester, yogaClass, faculty);
                sectionToBeCreated.setLocation(section.getLocation());
                sectionToBeCreated.setMaxStudents(section.getMaxStudents());
                sectionToBeCreated.setSchedule(section.getSchedule());
                sectionToBeCreated.setStart(section.getStart());
                sectionToBeCreated.setEnd(section.getEnd());
                sectionToBeCreated = sectionDAO.create(sectionToBeCreated);
            } else {
                throw new RecordAlreadyExistException("Section already exist with Location:"
                        + section.getLocation() + " and Schedule:" + section.getSchedule());
            }
        } else {
            throw new RequiredDataNotPresent("Required data not presenet to create Section");
        }
        return sectionToBeCreated;
    }
    
    @Override
    @Transactional
    public Section updateSection(Section section) throws RequiredDataNotPresent {
        Section sectionToBeUpdate = null;
        if ((section.getId() > 0) && !section.getSchedule().equals("") && section.getSchedule().equals("")) {
            sectionToBeUpdate = sectionDAO.getById(section.getId());
            sectionToBeUpdate.setSchedule(section.getSchedule());
            sectionToBeUpdate.setLocation(section.getLocation());
            sectionToBeUpdate.setStart(section.getStart());
            sectionToBeUpdate.setEnd(section.getEnd());
            sectionToBeUpdate = sectionDAO.update(sectionToBeUpdate);
        } else {
            throw new RequiredDataNotPresent("Required data not presenet to create section");
        }
        return sectionToBeUpdate;
    }
    
    @Override
    @Transactional
    public Section deleteSection(Section section) throws RequiredDataNotPresent {
        Section sectionDelete = null;
        if (section.getId() > 0) {
            sectionDelete = sectionDAO.remove(section.getId());
        } else {
            throw new RequiredDataNotPresent("Primery key not present");
        }
        return sectionDelete;
    }
    
    @Override
    @Transactional
    public List<Section> listOfAllSections() {
        return sectionDAO.list();
    }

    @Override
    public Section getSectionByID(int sectionID) {
        return sectionDAO.getById(sectionID);
    }
    
}
