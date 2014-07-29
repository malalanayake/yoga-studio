package com.app.spring.dao;

import java.util.List;

import com.app.spring.model.Customer;

/**
 * 
 * @author malalanayake
 * 
 */
public interface CustomerDAO {

	public Customer addCustomer(Customer p);

	public Customer updateCustomer(Customer p);

	public List<Customer> listCustomers();

	public Customer getCustomerById(int id);

	public Customer removeCustomer(int id);
}
