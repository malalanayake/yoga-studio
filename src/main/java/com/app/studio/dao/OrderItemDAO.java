package com.app.studio.dao;

import com.app.studio.model.OrderItem;

/**
 *
 * @author ahmadreza
 */

public interface OrderItemDAO {

    /**
     * Creates a new OrderItem record
     *
     * @param o - the orderItem to be created
     * @return the created orderItem object
     */
    OrderItem create(OrderItem o);

    /**
     * Updates an OrderItem record
     *
     * @param o - the orderItem with updated values
     * @return the updated orderItem object
     */
    OrderItem update(OrderItem o);

    /**
     * Returns the OrderItem record with the specified ID
     *
     * @param id - the OrderItem ID
     * @return the orderItem object with the specified ID
     */
    OrderItem getById(int id);

    /**
     * Deletes the OrderItem record with the specified ID
     *
     * @param id - the OrderItem ID
     * @return the deleted orderItem object
     */
    OrderItem remove(int id);
}
