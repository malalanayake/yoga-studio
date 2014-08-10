package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.exception.UnauthorizedOperation;
import com.app.studio.model.Customer;
import com.app.studio.model.Section;
import com.app.studio.model.WaiverRequest;
import java.util.List;

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

    public Customer getCustomerByUsername(String userName);

    public Section getSectionById(int id) throws RequiredDataNotPresent;

    public boolean enroll(String username, Section section) throws RequiredDataNotPresent, UnauthorizedOperation;

    public Section waitlist(String username, int sectionID) throws RequiredDataNotPresent;

    public Section drop(int enrolledSectionID) throws RequiredDataNotPresent, UnauthorizedOperation;

    /**
     * Retrieves the list of sections open for sign up
     *
     * @return
     */
    public List<Section> listAvailableSections();

}
