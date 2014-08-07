package com.app.studio.dao;

import java.util.List;

import com.app.studio.model.Customer;

/**
 * CustomerDAO operations
 *
 * @author malalanayake
 *
 */
public interface CustomerDAO {

    /**
     * Create a new customer
     *
     * @param p
     * @return
     */
    public Customer create(Customer p);

    /**
     * Update existing customer
     *
     * @param p
     * @return
     */
    public Customer update(Customer p);

    /**
     * List all customers
     *
     * @return
     */
    public List<Customer> list();

    /**
     * Get customer by ID
     *
     * @param id
     * @return
     */
    public Customer getById(int id);

    /**
     * Remove customer
     *
     * @param id
     * @return
     */
    public Customer remove(int id);

    /**
     * Get Customer by user name
     *
     * @param userName
     * @return
     */
    public Customer getByUserName(String userName);
}
