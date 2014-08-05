/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.studio.dao.impl;

import com.app.studio.dao.AdministratorDAO;
import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.model.Administrator;
import com.app.studio.model.Customer;
import com.app.studio.model.Faculty;
import com.app.studio.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Yen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/servlet-context-staging.xml"})
@Transactional
public class UserCreation {

    @Autowired
    private AdministratorDAO administratorDAO;
    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private UserDAO userDAO;

    @Test
    @Rollback(false)
    public void testCreate() {
        User adminUser = new User("a", "a", "Admin1", "User",
                "What is your favorit car?", "Benz");
        adminUser = userDAO.create(adminUser);

        Administrator admin = new Administrator(adminUser);
        admin = administratorDAO.create(admin);

        System.out.println("Created admin " + admin.getId());

        User facultyUser = new User("f", "f", "Faculty1", "User",
                "What is your favorit car?", "Benz");
        facultyUser = userDAO.create(facultyUser);

        Faculty faculty = new Faculty(facultyUser);
        faculty = facultyDAO.create(faculty);

        System.out.println("Created faculty " + faculty.getId());

        User customerUser = new User("c", "c", "Customer1", "User",
                "What is your favorit car?", "Benz");
        customerUser = userDAO.create(customerUser);

        Customer customer = new Customer(customerUser);
        customer = customerDAO.create(customer);

        System.out.println("Created customer " + customer.getId());
    }
}
