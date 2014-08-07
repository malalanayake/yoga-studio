package com.app.studio.service.impl;

import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import com.app.studio.service.WaiverRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jCalles
 */
@Service
public class WaiverRequestServiceImpl implements WaiverRequestService {

    private WaiverRequestDAO waiverRequestDAO;
    private YogaClassDAO yogaClassDAO;

    public YogaClassDAO getYogaClassDAO() {
        return yogaClassDAO;
    }

    public void setYogaClassDAO(YogaClassDAO yogaClassDAO) {
        this.yogaClassDAO = yogaClassDAO;
    }

    public WaiverRequestDAO getWaiverRequestDAO() {
        return waiverRequestDAO;
    }

    public void setWaiverRequestDAO(WaiverRequestDAO waiverRequestDAO) {
        this.waiverRequestDAO = waiverRequestDAO;
    }

    @Override
    @Transactional
    public WaiverRequest createNewWaiverRequest(YogaClass yogaclass, Customer customer) throws RequiredDataNotPresent {
        WaiverRequest waiverRequest = null;
        waiverRequest = waiverRequestDAO.create(waiverRequest);
        return waiverRequest;
    }

}
