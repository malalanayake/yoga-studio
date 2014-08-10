package com.app.studio.common;

import com.app.studio.dao.AdministratorDAO;
import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.EnrolledSectionDAO;
import com.app.studio.dao.FacultyDAO;
import com.app.studio.dao.OrderDAO;
import com.app.studio.dao.OrderItemDAO;
import com.app.studio.dao.ProductDAO;
import com.app.studio.dao.SectionDAO;
import com.app.studio.dao.SemesterDAO;
import com.app.studio.dao.ShoppingCartDAO;
import com.app.studio.dao.UserDAO;
import com.app.studio.dao.WaiverRequestDAO;
import com.app.studio.dao.YogaClassDAO;
import com.app.studio.model.Administrator;
import com.app.studio.model.Customer;
import com.app.studio.model.EnrolledSection;
import com.app.studio.model.Faculty;
import com.app.studio.model.Order;
import com.app.studio.model.OrderItem;
import com.app.studio.model.Product;
import com.app.studio.model.Section;
import com.app.studio.model.Semester;
import com.app.studio.model.ShoppingCart;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
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
public class PopulateDatabase {
    
    @Autowired
    private AdministratorDAO administratorDAO;
    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SemesterDAO semesterDAO;
    @Autowired
    private YogaClassDAO yogaClassDAO;
    @Autowired
    private SectionDAO sectionDAO;
    @Autowired
    private WaiverRequestDAO waiverRequestDAO;
    @Autowired
    private EnrolledSectionDAO enrolledSectionDAO;
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderItemDAO orderItemDAO;
    @Autowired
    private ProductDAO productDAO;
    
    @Test
    @Rollback(false)
    public void testCreate() {
        // Admin
        User adminUser = new User("a", "a", "Admin1", "User",
                "What is your favorit car?", "Benz");
        adminUser = userDAO.create(adminUser);
        Administrator a = new Administrator(adminUser);
        a = administratorDAO.create(a);
        System.out.println("Created admin " + a.getId());

        // Faculty
        User facultyUser = new User("f", "f", "Faculty1", "User",
                "What is your favorit car?", "Benz");
        facultyUser = userDAO.create(facultyUser);
        Faculty f = new Faculty(facultyUser);
        f = facultyDAO.create(f);
        System.out.println("Created faculty " + f.getId());

        // Customer
        User customerUser = new User("c", "c", "Customer1", "User",
                "What is your favorit car?", "Benz");
        customerUser = userDAO.create(customerUser);
        Customer c = new Customer(customerUser);
        c.setAddress("Fairfield, IA");
        c.setSignUpDate("2014-08-07");
        c.setAdvisor(f);
        c = customerDAO.create(c);
        System.out.println("Created customer " + c.getId());

        // Semester
        Semester semester1 = new Semester();
        semester1.setStartdate("2014-09-01");
        semester1.setEnddate("2014-09-30");
        semester1.setSignUpDate("2014-09-27");
        semester1 = semesterDAO.create(semester1);
        
        Semester semester2 = new Semester();
        semester2.setStartdate("2014-11-01");
        semester2.setEnddate("2014-11-30");
        semester2.setSignUpDate("2014-11-27");
        semester2 = semesterDAO.create(semester2);

        // Yoga Class
        YogaClass yogaA = new YogaClass();
        yogaA.setName("Yoga A");
        yogaA.setPrice(10);
        yogaA = yogaClassDAO.create(yogaA);
        
        YogaClass yogaB = new YogaClass();
        yogaB.setName("Yoga B");
        yogaB.setPrice(20);
        yogaB.addPrerequisite(yogaA);
        yogaB = yogaClassDAO.create(yogaB);
        
        YogaClass yogaC = new YogaClass();
        yogaC.setName("Yoga C");
        yogaC.setPrice(30);
        yogaC.addPrerequisite(yogaA);
        yogaC = yogaClassDAO.create(yogaC);
        
        YogaClass yogaD = new YogaClass();
        yogaD.setName("Yoga D");
        yogaD.setPrice(40);
        yogaD.addPrerequisite(yogaA);
        yogaD = yogaClassDAO.create(yogaD);
        
        YogaClass yogaE = new YogaClass();
        yogaE.setName("Yoga E");
        yogaE.setPrice(50);
        yogaE = yogaClassDAO.create(yogaE);
        
        YogaClass yogaF = new YogaClass();
        yogaF.setName("Yoga F");
        yogaF.setPrice(60);
        yogaF = yogaClassDAO.create(yogaF);

        // Section
        Section section1A = new Section(semester1, yogaA, f);
        section1A.setMaxStudents(20);
        section1A.setLocation("Hall A");
        section1A.setSchedule("MON");
        section1A.setStart("08:00am");
        section1A.setEnd("09:00am");
        section1A = sectionDAO.create(section1A);
        
        Section section1B = new Section(semester1, yogaB, f);
        section1B.setMaxStudents(20);
        section1B.setLocation("Hall B");
        section1B.setSchedule("TUE");
        section1B.setStart("08:00am");
        section1B.setEnd("09:00am");
        section1B = sectionDAO.create(section1B);
        
        Section section1C = new Section(semester1, yogaC, f);
        section1C.setMaxStudents(20);
        section1C.setLocation("Hall C");
        section1C.setSchedule("WED");
        section1C.setStart("08:00am");
        section1C.setEnd("09:00am");
        section1C = sectionDAO.create(section1C);
        
        Section section1D = new Section(semester1, yogaD, f);
        section1D.setMaxStudents(20);
        section1D.setLocation("Hall D");
        section1D.setSchedule("THR");
        section1D.setStart("08:00am");
        section1D.setEnd("09:00am");
        section1D = sectionDAO.create(section1D);
        
        Section section1E = new Section(semester1, yogaE, f);
        section1E.setMaxStudents(20);
        section1E.setLocation("Hall E");
        section1E.setSchedule("FRI");
        section1E.setStart("08:00am");
        section1E.setEnd("09:00am");
        section1E = sectionDAO.create(section1E);
        
        Section section1F = new Section(semester1, yogaF, f);
        section1F.setMaxStudents(20);
        section1F.setLocation("Hall F");
        section1F.setSchedule("SAT");
        section1F.setStart("08:00am");
        section1F.setEnd("09:00am");
        section1F = sectionDAO.create(section1F);

        // Waivers
        WaiverRequest waiverB = new WaiverRequest(yogaB, c);
        waiverB.setStatus(WaiverRequest.Constants.STATUS_PENDING);
        waiverB = waiverRequestDAO.create(waiverB);
        
        WaiverRequest waiverC = new WaiverRequest(yogaC, c);
        waiverC.setStatus(WaiverRequest.Constants.STATUS_PENDING);
        waiverC = waiverRequestDAO.create(waiverC);
        
        WaiverRequest waiverD = new WaiverRequest(yogaD, c);
        waiverD.setStatus(WaiverRequest.Constants.STATUS_PENDING);
        waiverD = waiverRequestDAO.create(waiverD);

        // Enrolled Section
        EnrolledSection enrolled = new EnrolledSection(c, section1E);
        enrolled.setDate("2014-08-08");
        enrolled.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled = enrolledSectionDAO.create(enrolled);
        
        EnrolledSection waitlisted = new EnrolledSection(c, section1F);
        waitlisted.setDate("2014-08-08");
        waitlisted.setStatus(EnrolledSection.Constants.STATUS_WAITLISTED);
        waitlisted = enrolledSectionDAO.create(waitlisted);

        // Shopping Cart
        ShoppingCart cart = new ShoppingCart(c);
        cart = shoppingCartDAO.create(cart);

        // Product
        Product product = new Product();
        product.setName("Yoga Mat");
        product.setType("Exercise");
        product.setPrice(10);
        product.setAvailableQuantity(50);
        product.setDescription("Purple yoga mat");
        product = productDAO.create(product);

        // Order
        Order order = new Order();
        order.setCustomer(c);
        order.setStatus(Order.Constants.STATUS_PROCESSING);
        order.setTotalPrice(23);

        // Order Item
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setQuantity(1);
        item.setProduct(product);
        
        order = orderDAO.create(order);
    }

//    @Test
//    @Rollback(false)
//    public void testUpdate() {
//        // Prerequisites
//        YogaClass prereq = yogaClassDAO.getById(7);
//        YogaClass yoga = yogaClassDAO.getById(9);
//        
//        yoga.addPrerequisite(prereq);
//        yoga = yogaClassDAO.update(yoga);
//    }
}
