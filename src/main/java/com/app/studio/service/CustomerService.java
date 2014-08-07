package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.WaiverRequest;
import com.app.studio.security.Roles;
import java.util.List;
import static javassist.CtMethod.ConstParameter.string;
import org.springframework.security.access.annotation.Secured;

/**
 * Interface which is providing the customer service operations
 * 
 * @author malalanayake
 * 
 */
public interface CustomerService {

	public Customer addCustomer(Customer p) throws RequiredDataNotPresent, RecordAlreadyExistException;

	public void updateCustomer(Customer p);

	public List<Customer> listCustomers();

	public Customer getCustomerById(int id);

	public void removeCustomer(int id);
        
        public WaiverRequest addWaiverRequest();
        
        public Customer getCustomerByUsername (String userName);
        
        

}
