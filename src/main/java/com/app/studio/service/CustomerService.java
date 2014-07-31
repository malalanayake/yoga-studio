package com.app.studio.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.app.studio.model.Customer;
import com.app.studio.security.Roles;

/**
 * Interface which is providing the customer service operations
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
