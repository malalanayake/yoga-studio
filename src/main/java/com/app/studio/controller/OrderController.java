package com.app.studio.controller;

import com.app.studio.model.Order;
import com.app.studio.model.Semester;
import com.app.studio.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.app.studio.model.Customer;
import com.app.studio.service.CustomerService;
import java.security.Principal;

/**
 * Control the orders
 *
 * @author malalanayake
 */
@Controller
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;

    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public CustomerService getCustomerService() {
        return customerService;
    }

    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired(required = true)
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * View Orders
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/view-orders", method = RequestMethod.GET)
    public String listOrders(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("listOrders", this.orderService.listOfAllOrders());
        return "view-orders";
    }

    /**
     * View Orders by ID
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/view-orders/{id}", method = RequestMethod.GET)
    public String viewOrderItems(@PathVariable("id") int id, Model model) {
        Order order = this.orderService.getById(id);
        model.addAttribute("listOrderItems", order.getSetOfOrderItems());
        return "view-orders";
    }

    /**
     * View Orders from Customer
     *
     * @param customer
     * @param model
     * @return
     */
    @RequestMapping(value = "/view-orders-customer", method = RequestMethod.GET)
    public String listOrdersCustomer(Model model, Principal user) {
        Customer customer = customerService.getCustomerByUsername(user.getName());
        model.addAttribute("orderCustomer", new Order());
        model.addAttribute("username", customer.getUser().getFirstName());
        model.addAttribute("listCustomerOrders", this.orderService.listOfAllOrdersCustomer(customer));
        return "view-orders-customer";
    }

    /**
     * View Orders from Customer
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/view-orders-customer/{id}", method = RequestMethod.GET)
    public String viewOrderItemsCustomer(@PathVariable("id") int id, Model model) {
        Order order = this.orderService.getById(id);
        model.addAttribute("listOrderItemsCustomer", this.orderService.listOfAllOrderItemsCustomer(order));
        return "view-orders-customer";
    }
}
