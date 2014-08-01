package com.app.studio.dao;

import com.app.studio.model.WaiverRequest;
import java.util.List;

/**
 * Interface which is provide the operation for Waiver Request
 *
 * @author malalanayake
 */
public interface WaiverRequestDAO {

    /**
     * Create a new WaiverRequest
     *
     * @param p
     * @return
     */
    public WaiverRequest create(WaiverRequest p);

    /**
     * Update existing WaiverRequest
     *
     * @param p
     * @return
     */
    public WaiverRequest update(WaiverRequest p);

    /**
     * List all WaiverRequest
     *
     * @return
     */
    public List<WaiverRequest> list();

    /**
     * Get WaiverRequest by ID
     *
     * @param id
     * @return
     */
    public WaiverRequest getById(int id);

    /**
     * Remove WaiverRequest
     *
     * @param id
     * @return
     */
    public WaiverRequest remove(int id);
}
