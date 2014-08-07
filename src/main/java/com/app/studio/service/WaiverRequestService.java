
package com.app.studio.service;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;

/**
 *
 * @author jCalles
 */
public interface WaiverRequestService {
    
 
    /**
     * Create new WaiverRequest
     *
     * @param yogaclass
     * @param customer
     * @return
     * @throws RequiredDataNotPresent
     */
    public WaiverRequest createNewWaiverRequest(YogaClass yogaclass, Customer customer) throws RequiredDataNotPresent;
           

  

    
   
}
    

