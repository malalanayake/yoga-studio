package com.app.studio.service.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.security.Roles;
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

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    //SignUp Service
    @Override
    @Transactional
    public Customer addCustomer(Customer c) throws RequiredDataNotPresent, RecordAlreadyExistException {
        c.setSignUpDate(new Date().toString());
        User u = c.getUser();
        if (!u.getFirstName().equals("") && !u.getLastName().equals("") && !u.getUsername().equals("") && !u.getPassword().equals("")
                && !u.getSequrityQuestion().equals("") && !u.getAnswer().equals("")) {
            if (userDAO.getByUserName(c.getUser().getUsername()) == null) {
                User user = userDAO.create(u);
                c.setUser(user);
                return this.customerDAO.create(c);
            } else {
                throw new RecordAlreadyExistException("User already exists");
            }
        } else {
            throw new RequiredDataNotPresent("You have to complete all the fields");
        }
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
    
    @Override
    public WaiverRequest addWaiverRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Customer getCustomerByUsername(String userName) {
        return this.customerDAO.getByUserName(userName);
    
}

}