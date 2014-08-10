package com.app.studio.dao;

import com.app.studio.model.Order;
import java.util.List;

/**
 * OrderDAO operations
 *
 * @author Yen
 */
public interface OrderDAO {

    /**
     * Creates a new Order record
     *
     * @param o - the order to be created
     * @return the created order object
     */
    Order create(Order o);

    /**
     * Updates an Order record
     *
     * @param o - the order with updated values
     * @return the updated order object
     */
    Order update(Order o);

    /**
     * Returns a list of all Order records
     *
     * @return a list of all order objects
     */
    List<Order> list();

    /**
     * Returns the Order record with the specified ID
     *
     * @param id - the Order ID
     * @return the order object with the specified ID
     */
    Order getById(int id);

    /**
     * Deletes the Order record with the specified ID
     *
     * @param id - the Order ID
     * @return the deleted order object
     */
    Order remove(int id);

    /**
     * List by status
     *
     * @param status
     * @return
     */
    List<Order> listByStatus(String status);
}
