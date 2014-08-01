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

	public Customer create(Customer p);

	public Customer update(Customer p);

	public List<Customer> list();

	public Customer getById(int id);

	public Customer remove(int id);
}
