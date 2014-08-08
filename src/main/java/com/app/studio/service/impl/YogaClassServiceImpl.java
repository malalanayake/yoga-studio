package com.app.studio.service.impl;

import com.app.studio.dao.YogaClassDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.YogaClass;
import com.app.studio.service.YogaClassService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jCalles
 */
public class YogaClassServiceImpl implements YogaClassService {

    private YogaClassDAO yogaClassDAO;

    public YogaClassDAO getYogaClassDAO() {
        return yogaClassDAO;
    }

    public void setYogaClassDAO(YogaClassDAO yogaClassDAO) {
        this.yogaClassDAO = yogaClassDAO;
    }

    @Override
    @Transactional
    public YogaClass createYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent, RecordAlreadyExistException {
        YogaClass yoga = null;
        if (!yogaClass.getName().equals("") && (yogaClass.getPrice() > 0)) {
            if (yogaClassDAO.getByName(yogaClass.getName()) != null) {
                yoga = yogaClassDAO.create(yogaClass);
            } else {
                throw new RecordAlreadyExistException("Yoga class name already exist");
            }
        } else {
            throw new RequiredDataNotPresent("Required data not presenet to create yoga class");
        }
        return yoga;

    }

    @Override
    @Transactional
    public YogaClass updateYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent {
        YogaClass yoga = null;
        if ((yogaClass.getId() > 0) && !yogaClass.getName().equals("") && (yogaClass.getPrice() > 0)) {
            yoga = yogaClassDAO.getById(yogaClass.getId());
            yoga.setName(yogaClass.getName());
            yoga.setPrice(yogaClass.getPrice());
            yoga = yogaClassDAO.update(yoga);
        } else {
            throw new RequiredDataNotPresent("Required data not presenet to create semester");
        }
        return yoga;
    }

    @Override
    @Transactional
    public YogaClass deleteYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent {
        YogaClass yoga = null;
        if (yogaClass.getId() > 0) {
            yoga = yogaClassDAO.remove(yogaClass.getId());
        } else {
            throw new RequiredDataNotPresent("Primery key not present");
        }
        return yoga;
    }

    @Override
    @Transactional
    public List<YogaClass> listOfYogaClasses() {
        return yogaClassDAO.list();

    }

    @Override
    @Transactional
    public YogaClass getYogaClassByID(int yogaClassID) throws RequiredDataNotPresent {
        YogaClass yogaclass = yogaClassDAO.getById(yogaClassID);
        if (yogaclass != null) {
            return yogaclass;
        } else {
            throw new RequiredDataNotPresent("Primary key not present");
        }
    }

}
