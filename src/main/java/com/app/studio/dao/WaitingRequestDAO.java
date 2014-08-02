package com.app.studio.dao;

import com.app.studio.model.WaitingRequest;
import java.util.List;

/**
 *
 * @author jCalles
 */
public interface WaitingRequestDAO {

    /**
     * Create a new Waiting Request
     *
     * @param waitingRequest
     * @return
     */
    public WaitingRequest create(WaitingRequest waitingRequest);

    /**
     * Update existing Waiting Request
     *
     * @param waitingRequest
     * @return
     */
    public WaitingRequest update(WaitingRequest waitingRequest);

    /**
     * Get Waiting Request by ID
     *
     * @param id
     * @return
     */
    public WaitingRequest getById(int id);

    /**
     * Remove Waiting Request
     *
     * @param id
     * @return
     */
    public WaitingRequest remove(int id);

    /**
     * List all Waiting Request
     *
     * @return
     */
}
