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
import com.app.studio.dao.ShoppingCartItemDAO;
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
import com.app.studio.model.ShoppingCartItem;
import com.app.studio.model.User;
import com.app.studio.model.WaiverRequest;
import com.app.studio.model.YogaClass;
import static org.junit.Assert.assertEquals;
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
    private ShoppingCartItemDAO shoppingCartItemDAO;
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
        User adminUser = new User("admin", "admin", "Michael", "Smith",
                "What is your favorit car?", "Benz");
        adminUser = userDAO.create(adminUser);
        Administrator a = new Administrator(adminUser);
        a = administratorDAO.create(a);
        System.out.println("Created admin " + a.getId());

        // Faculty
        User facultyUser = new User("jlerman", "123", "Joseph", "Lerman",
                "What is your favorit car?", "Mercedes");
        facultyUser = userDAO.create(facultyUser);
        Faculty f1 = new Faculty(facultyUser);
        f1 = facultyDAO.create(f1);
        System.out.println("Created faculty " + f1.getId());

        User facultyUser2 = new User("ecorazza", "123", "Erick", "Corazza",
                "What is the middle name of your mother?", "Susie");
        facultyUser2 = userDAO.create(facultyUser2);
        Faculty f2 = new Faculty(facultyUser2);
        f2 = facultyDAO.create(f2);
        System.out.println("Created faculty " + f2.getId());

        User facultyUser3 = new User("jstone", "123", "James", "Stone",
                "What is your favorite Yoga Pose?", "Lotus");
        facultyUser3 = userDAO.create(facultyUser3);
        Faculty f3 = new Faculty(facultyUser3);
        f3 = facultyDAO.create(f3);
        System.out.println("Created faculty " + f3.getId());

        // Customer
        User customerUser = new User("yplagata", "123", "Yen", "Plagata",
                "What is your favorit band?", "Beattles");
        customerUser = userDAO.create(customerUser);
        Customer c1 = new Customer(customerUser);
        c1.setAddress("Fairfield, IA");
        c1.setSignUpDate("2014-08-08");
        c1.setAdvisor(f1);
        c1 = customerDAO.create(c1);
        System.out.println("Created customer " + c1.getId());

        // Customer2
        User customerUser2 = new User("jcalles", "123", "Juan", "Calles",
                "What is your favorit car?", "BMW");
        customerUser2 = userDAO.create(customerUser2);
        Customer c2 = new Customer(customerUser2);
        c2.setAddress("Fairfield, IA");
        c2.setSignUpDate("2013-01-05");
        c2.setAdvisor(f2);
        c2 = customerDAO.create(c2);
        System.out.println("Created customer " + c2.getId());

        // Customer3
        User customerUser3 = new User("dyepez", "123", "Diana", "Yepez",
                "What is your favorit food?", "Pizza");
        customerUser3 = userDAO.create(customerUser3);
        Customer c3 = new Customer(customerUser3);
        c3.setAddress("Fairfield, IA");
        c3.setSignUpDate("2010-08-11");
        c3.setAdvisor(f1);
        c3 = customerDAO.create(c3);
        System.out.println("Created customer " + c3.getId());

        // Customer4
        User customerUser4 = new User("sparker", "123", "Sarah", "Parker",
                "What is your favorit food?", "Sushi");
        customerUser4 = userDAO.create(customerUser4);
        Customer c4 = new Customer(customerUser4);
        c4.setAddress("Fairfield, IA");
        c4.setSignUpDate("2010-08-11");
        c4.setAdvisor(f1);
        c4 = customerDAO.create(c4);
        System.out.println("Created customer " + c4.getId());

        // Customer5
        User customerUser5 = new User("lperez", "123", "Lindsay", "Perez",
                "What is your favorit food?", "Tamales");
        customerUser5 = userDAO.create(customerUser5);
        Customer c5 = new Customer(customerUser5);
        c5.setAddress("Fairfield, IA");
        c5.setSignUpDate("2010-08-11");
        c5 = customerDAO.create(c5);
        System.out.println("Created customer " + c5.getId());

        // Semester
        Semester semester1 = new Semester();
        semester1.setStartdate("2014-08-15");
        semester1.setEnddate("2014-09-15");
        semester1.setSignUpDate("2014-08-11");
        semester1 = semesterDAO.create(semester1);

        Semester semester2 = new Semester();
        semester2.setStartdate("2014-10-06");
        semester2.setEnddate("2014-11-04");
        semester2.setSignUpDate("2014-10-01");
        semester2 = semesterDAO.create(semester2);

        // Yoga Class
        YogaClass yogaA = new YogaClass();
        yogaA.setName("Introduction to Yoga");
        yogaA.setPrice(10);
        yogaA = yogaClassDAO.create(yogaA);

        YogaClass yogaB = new YogaClass();
        yogaB.setName("Prenatal Preparing for Birth");
        yogaB.setPrice(20);
        yogaB = yogaClassDAO.create(yogaB);
        
        YogaClass yogaC = new YogaClass();
        yogaC.setName("Sacred Expansion");
        yogaC.setPrice(20);
        yogaC = yogaClassDAO.create(yogaC);
        
        YogaClass yogaD = new YogaClass();
        yogaD.setName("Gravity and Grace");
        yogaD.setPrice(15);
        yogaD.addPrerequisite(yogaA);
        yogaD = yogaClassDAO.create(yogaD);

        YogaClass yogaE = new YogaClass();
        yogaE.setName("Hot Yoga");
        yogaE.setPrice(25);
        yogaE.addPrerequisite(yogaC);
        yogaE = yogaClassDAO.create(yogaE);

        YogaClass yogaF = new YogaClass();
        yogaF.setName("Advanced Positions");
        yogaF.addPrerequisite(yogaA);
        yogaF.addPrerequisite(yogaC);
        yogaF.setPrice(25);
        yogaF = yogaClassDAO.create(yogaF);
        
        YogaClass yogaG = new YogaClass();
        yogaG.setName("Yoga SCI");
        yogaG.addPrerequisite(yogaE);
        yogaG.setPrice(25);
        yogaG = yogaClassDAO.create(yogaG);
        
        YogaClass yogaH = new YogaClass();
        yogaH.setName("Yoga for Sidhis");
        yogaH.addPrerequisite(yogaG);
        yogaH.setPrice(50);
        yogaH = yogaClassDAO.create(yogaH);
        

        // Section
        Section section1A = new Section(semester2, yogaA, f1);
        section1A.setMaxStudents(20);
        section1A.setLocation("Hall A");
        section1A.setSchedule("MON");
        section1A.setStart("08:00am");
        section1A.setEnd("09:00am");
        section1A = sectionDAO.create(section1A);

        Section section1B = new Section(semester2, yogaB, f1);
        section1B.setMaxStudents(15);
        section1B.setLocation("Hall B");
        section1B.setSchedule("TUE");
        section1B.setStart("08:00am");
        section1B.setEnd("09:00am");
        section1B = sectionDAO.create(section1B);

        Section section1C = new Section(semester2, yogaC, f2);
        section1C.setMaxStudents(2);
        section1C.setLocation("Hall C");
        section1C.setSchedule("WED");
        section1C.setStart("08:00am");
        section1C.setEnd("09:00am");
        section1C = sectionDAO.create(section1C);

        Section section1D = new Section(semester1, yogaD, f2);
        section1D.setMaxStudents(15);
        section1D.setLocation("Hall D");
        section1D.setSchedule("THR");
        section1D.setStart("08:00am");
        section1D.setEnd("09:00am");
        section1D = sectionDAO.create(section1D);

        Section section1E = new Section(semester2, yogaE, f3);
        section1E.setMaxStudents(15);
        section1E.setLocation("Hall E");
        section1E.setSchedule("FRI");
        section1E.setStart("08:00am");
        section1E.setEnd("09:00am");
        section1E = sectionDAO.create(section1E);

        Section section1F = new Section(semester2, yogaF, f3);
        section1F.setMaxStudents(20);
        section1F.setLocation("Hall F");
        section1F.setSchedule("SAT");
        section1F.setStart("08:00am");
        section1F.setEnd("09:00am");
        section1F = sectionDAO.create(section1F);
        
        Section section1G = new Section(semester2, yogaG, f3);
        section1G.setMaxStudents(20);
        section1G.setLocation("Hall G");
        section1G.setSchedule("SUN");
        section1G.setStart("08:00am");
        section1G.setEnd("09:00am");
        section1G = sectionDAO.create(section1G);
        
        Section section1H = new Section(semester2, yogaH, f3);
        section1H.setMaxStudents(20);
        section1H.setLocation("Hall G");
        section1H.setSchedule("SUN");
        section1H.setStart("10:00am");
        section1H.setEnd("12:00am");
        section1H = sectionDAO.create(section1H);
        
        
        
       
        // Waivers
       

        WaiverRequest waiverD = new WaiverRequest(yogaF, c1);
        waiverD.setStatus(WaiverRequest.Constants.STATUS_APPROVED);
        waiverD = waiverRequestDAO.create(waiverD);
        
        WaiverRequest waiverF = new WaiverRequest(yogaG, c1);
        waiverF.setStatus(WaiverRequest.Constants.STATUS_REJECTED);
        waiverF = waiverRequestDAO.create(waiverF);
        
        
        

        // Enrolled Section
        EnrolledSection enrolled = new EnrolledSection(c1, section1A);
        enrolled.setDate("2014-08-12");
        enrolled.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled = enrolledSectionDAO.create(enrolled);

        EnrolledSection enrolled2 = new EnrolledSection(c1, section1B);
        enrolled2.setDate("2014-08-12");
        enrolled2.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled2 = enrolledSectionDAO.create(enrolled2);
        
        EnrolledSection enrolled9 = new EnrolledSection(c1, section1D);
        enrolled9.setDate("2014-08-12");
        enrolled9.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled9 = enrolledSectionDAO.create(enrolled9);
        

        EnrolledSection enrolled3 = new EnrolledSection(c2, section1A);
        enrolled3.setDate("2014-08-12");
        enrolled3.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled3 = enrolledSectionDAO.create(enrolled3);

        EnrolledSection enrolled4 = new EnrolledSection(c2, section1C);
        enrolled4.setDate("2014-08-12");
        enrolled4.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled4 = enrolledSectionDAO.create(enrolled4);

        EnrolledSection enrolled5 = new EnrolledSection(c3, section1A);
        enrolled5.setDate("2014-08-12");
        enrolled5.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled5 = enrolledSectionDAO.create(enrolled5);

        EnrolledSection enrolled6 = new EnrolledSection(c3, section1C);
        enrolled6.setDate("2014-08-12");
        enrolled6.setStatus(EnrolledSection.Constants.STATUS_ENROLLED);
        enrolled6 = enrolledSectionDAO.create(enrolled6);

      
        EnrolledSection enrolled8 = new EnrolledSection(c4, section1C);
        enrolled8.setDate("2014-08-12");
        enrolled8.setStatus(EnrolledSection.Constants.STATUS_WAITLISTED);
        enrolled8 = enrolledSectionDAO.create(enrolled8);

        // Shopping Cart
        ShoppingCart cart = new ShoppingCart(c1);
        cart = shoppingCartDAO.create(cart);

        // Product
        Product product = new Product();
        product.setName("Yoga Carpet");
        product.setType("Exercise");
        product.setImageSrc("http://cdn.shopclues.com/images/detailed/777/yogacomposite_1373652579.jpg");
        product.setPrice(10);
        product.setAvailableQuantity(50);
        product.setDescription("Blue Yoga Carpet");
        product = productDAO.create(product);
        
        Product product2 = new Product();
        product2.setName("Yoga Shoes");
        product2.setType("Shoes");
        product2.setImageSrc("http://cf-resrc.outsideonline.com/S=W800,U/C=W100P,H100P/O=90,P/http://media.outsideonline.com/images/nike-studio_fe.jpg");
        product2.setPrice(20);
        product2.setAvailableQuantity(50);
        product2.setDescription("Amazing Yoga Shoes");
        product2 = productDAO.create(product2);
        
        Product product3 = new Product();
        product3.setName("Yoga Carpet");
        product3.setType("Exercise");
        product3.setImageSrc("http://www.hometone.com/wp-content/uploads/2013/05/gaiam-yoga-mat1_5oLnJ_1822.jpg");
        product3.setPrice(16);
        product3.setAvailableQuantity(10);
        product3.setDescription("Green Yoga Carpet");
        product3 = productDAO.create(product3);
        
        Product product4 = new Product();
        product4.setName("Yoga Kit");
        product4.setType("Exercise");
        product4.setImageSrc("http://www.awesomlife.com/images/yogamatpg2.jpg");
        product4.setPrice(40);
        product4.setAvailableQuantity(6);
        product4.setDescription("Yoga Kit");
        product4 = productDAO.create(product4);
       
        // Order
        Order order = new Order();
        order.setCustomer(c1);
        order.setStatus(Order.Constants.STATUS_PROCESSING);
        order.setTotalPrice(23);

        // Order Item
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setQuantity(1);
        item.setProduct(product2);

        order = orderDAO.create(order);
    }

    @Test
    @Rollback(false)
    public void testShopping() {
        User customerUser = new User("c", "c", "Customer1", "User",
                "What is your favorit car?", "Benz");
        customerUser = userDAO.create(customerUser);
        Customer c = new Customer(customerUser);
        c.setAddress("Fairfield, IA");
        c.setSignUpDate("2014-08-07");
        c = customerDAO.create(c);
        System.out.println("Created customer " + c.getId());

        ShoppingCart cart = new ShoppingCart(c);
        cart = shoppingCartDAO.create(cart);

        c = customerDAO.getById(c.getId());
        System.out.println(cart.toString());
        System.out.println(c.toString());
        assertEquals(cart.getId(), c.getShoppingCart().getId());
        assertEquals(cart.getId(), cart.getCustomer().getShoppingCart().getId());

        Product product = new Product();
        product.setName("Yoga Carpet");
        product.setType("Exercise");
        product.setImageSrc("http://cdn.shopclues.com/images/detailed/777/yogacomposite_1373652579.jpg");
        product.setPrice(10);
        product.setAvailableQuantity(50);
        product.setDescription("Blue Yoga Carpet");
        product = productDAO.create(product);
        
        Product product2 = new Product();
        product2.setName("Yoga Shoes");
        product2.setType("Shoes");
        product2.setImageSrc("http://cf-resrc.outsideonline.com/S=W800,U/C=W100P,H100P/O=90,P/http://media.outsideonline.com/images/nike-studio_fe.jpg");
        product2.setPrice(20);
        product2.setAvailableQuantity(50);
        product2.setDescription("Amazing Yoga Shoes");
        product2 = productDAO.create(product2);
        
        Product product3 = new Product();
        product3.setName("Yoga Carpet");
        product3.setType("Exercise");
        product3.setImageSrc("http://www.hometone.com/wp-content/uploads/2013/05/gaiam-yoga-mat1_5oLnJ_1822.jpg");
        product3.setPrice(16);
        product3.setAvailableQuantity(10);
        product3.setDescription("Green Yoga Carpet");
        product3 = productDAO.create(product3);
        
        Product product4 = new Product();
        product4.setName("Yoga Kit");
        product4.setType("Exercise");
        product4.setImageSrc("http://www.awesomlife.com/images/yogamatpg2.jpg");
        product4.setPrice(40);
        product4.setAvailableQuantity(6);
        product4.setDescription("Yoga Kit");
        product4 = productDAO.create(product4);
        
        // Shopping Cart Tests
        ShoppingCartItem item1 = new ShoppingCartItem();
        item1.setProduct(product);
        item1.setQuantity(1);
        item1.setShoppingCart(cart);
        item1 = shoppingCartItemDAO.create(item1);
        assertEquals(cart.getId(), item1.getShoppingCart().getId());

        ShoppingCartItem item2 = new ShoppingCartItem();
        item2.setProduct(product2);
        item2.setQuantity(1);
        item2.setShoppingCart(cart);
        item2 = shoppingCartItemDAO.create(item2);
        assertEquals(cart.getId(), item2.getShoppingCart().getId());
    }
}
