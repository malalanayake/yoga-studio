package com.app.studio.service;

import java.util.List;

import com.app.studio.model.Customer;

/**
 * 
 * @author malalanayake
 * 
 */
public interface CustomerService {

	public void addCustomer(Customer p);

	public void updateCustomer(Customer p);

	public List<Customer> listCustomers();

	public Customer getCustomerById(int id);

	public void removeCustomer(int id);

}
