package com.app.studio.dao;

import java.util.List;

import com.app.studio.model.Customer;

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
