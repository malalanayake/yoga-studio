package com.app.studio.service.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
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
        private UserDAO userDAO;

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

        //SignUp Service
	@Override
	@Transactional
	public Customer addCustomer(Customer c) throws RequiredDataNotPresent, RecordAlreadyExistException {
                c.setSignUpDate(new Date().toString());
                if(userDAO.getByUserName(c.getUser().getUsername()) == null)
		return this.customerDAO.create(c);
                else
                throw new RecordAlreadyExistException("User already exists");
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
