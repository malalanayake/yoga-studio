package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.Order;
import com.app.studio.model.OrderItem;
import java.util.List;

/**
 * Interface which is going to provide the Order service operations
 *
 * @author malalanayake
 */
public interface OrderService {

    /**
     * Create new Order
     *
     * @param customer
     * @return
     * @throws RequiredDataNotPresent
     * @throws RecordAlreadyExistException
     */
    public Order createNewOrder(Customer customer) throws RequiredDataNotPresent,
            RecordAlreadyExistException;

    /**
     * Add new order item
     *
     * @param order
     * @param orderItem
     * @return
     * @throws RequiredDataNotPresent
     */
    public Order addNewOrderItem(Order order, OrderItem orderItem) throws RequiredDataNotPresent;

    /**
     * Update order
     *
     * @param order
     * @return
     * @throws RequiredDataNotPresent
     */
    public Order updateOrder(Order order) throws RequiredDataNotPresent;

    /**
     * Update order item
     *
     * @param orderItem
     * @return
     * @throws RequiredDataNotPresent
     */
    public OrderItem updateOrderItem(OrderItem orderItem) throws RequiredDataNotPresent;

    /**
     * Delete order item
     *
     * @param orderItem
     * @return
     * @throws RequiredDataNotPresent
     */
    public Order deleteOrderItem(OrderItem orderItem) throws RequiredDataNotPresent;

    /**
     * Delete order
     *
     * @param order
     * @return
     * @throws RequiredDataNotPresent
     */
    public Order deleteOrder(Order order) throws RequiredDataNotPresent;

    /**
     * List all orders
     *
     * @return
     */
    public List<Order> listOfAllOrders();

    /**
     * List all orders according to the status
     *
     * @param status
     * @return
     */
    public List<Order> listOfAllOrdersAccordingToStatus(String status);

    /**
     * Get order by id
     *
     * @param id
     * @return
     */
    public Order getById(int id);
}
