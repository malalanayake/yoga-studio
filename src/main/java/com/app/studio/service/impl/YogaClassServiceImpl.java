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
public class YogaClassServiceImpl implements YogaClassService{

    public YogaClassDAO getYogaClassDAO() {
        return yogaClassDAO;
    }

    public void setYogaClassDAO(YogaClassDAO yogaClassDAO) {
        this.yogaClassDAO = yogaClassDAO;
    }
    
    private YogaClassDAO yogaClassDAO;
   

    @Override
   @Transactional
   public YogaClass createYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent, RecordAlreadyExistException {
    
        return yogaClass;
   
   }

    @Override
    public YogaClass updateYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public YogaClass deleteYogaClass(YogaClass yogaClass) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<YogaClass> listOfYogaClasses() {
       return yogaClassDAO.list();
        
    }

    @Override
    public YogaClass getYogaClassByID(int yogaClassID) throws RequiredDataNotPresent {
             YogaClass yogaclass= yogaClassDAO.getById(yogaClassID);
        if (yogaclass != null) {
            return yogaclass;
        } else {
            throw new RequiredDataNotPresent("Primary key not present");
        }
    }
    
    
    
}
