package com.app.studio.service.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.model.Customer;
import com.app.studio.service.CustomerService;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom user detail service which is going to provide the user details to the
 * spring framework
 * 
 * @author malalanayake
 * 
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO;

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	@Transactional
	public void addCustomer(Customer c) {
                c.setSignUpDate(new Date().toString());
		this.customerDAO.create(c);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer c) {
		this.customerDAO.update(c);
	}

	@Override
	@Transactional
	public List<Customer> listCustomers() {
		return this.customerDAO.list();
	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		return this.customerDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeCustomer(int id) {
		this.customerDAO.remove(id);
	}

}
