package com.app.studio.dao;

import com.app.studio.model.WaiverRequest;
import java.util.List;

/**
 * Interface which is provide the operation for Waiver Request
 *
 * @author malalanayake
 */
public interface WaiverRequestDAO {

    public WaiverRequest create(WaiverRequest p);

    public WaiverRequest update(WaiverRequest p);

    public List<WaiverRequest> list();

    public WaiverRequest getById(int id);
    
    public WaiverRequest remove(int id);
}
