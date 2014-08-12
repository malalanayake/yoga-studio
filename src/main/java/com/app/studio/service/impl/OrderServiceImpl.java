package com.app.studio.service.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.OrderDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.Order;
import com.app.studio.model.OrderItem;
import com.app.studio.service.OrderService;
import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dmalalanayake
 */
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO;
    CustomerDAO customerDAO;

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    @Transactional
    public Order createNewOrder(Customer customer) throws RequiredDataNotPresent, RecordAlreadyExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Order addNewOrderItem(Order order, OrderItem orderItem) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public OrderItem updateOrderItem(OrderItem orderItem) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Order deleteOrderItem(OrderItem orderItem) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Order deleteOrder(Order order) throws RequiredDataNotPresent {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Order> listOfAllOrders() {
        return orderDAO.list();
    }

    @Override
    @Transactional
    public Set<Order> listOfAllOrdersCustomer(Customer customer) {
        return customer.getSetOfOrders();
    }

    @Override
    @Transactional
    public List<Order> listOfAllOrdersAccordingToStatus(String status) {
        return orderDAO.listByStatus(status);
    }

    @Override
    @Transactional
    public Order getById(int id) {
        return orderDAO.getById(id);
    }

    @Override
    @Transactional
    public Set<OrderItem> listOfAllOrderItemsCustomer(Order order) {
        Order orderLatest= orderDAO.getById(order.getId());
        return orderLatest.getSetOfOrderItems();
    }

}
