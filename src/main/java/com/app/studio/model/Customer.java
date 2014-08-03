package com.app.studio.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class which is holding the customer data
 *
 * @author malalanayake
 *
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String signUpDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty advisor;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<WaiverRequest> setOfWaiverRequests;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<EnrolledSection> setOfEnrolledSections;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<WaitingRequest> setOfWaitingRequests;
    @OneToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Order> setOfOrders;

    public Customer(User user) {
        this.user = user;
        this.setOfEnrolledSections = new HashSet<EnrolledSection>();
        this.setOfWaiverRequests = new HashSet<WaiverRequest>();
        this.setOfWaitingRequests = new HashSet<WaitingRequest>();
        this.setOfOrders = new HashSet<Order>();
    }

    public User getUser() {
        return user;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addWaitingRequest(WaitingRequest waitingRequest) {
        this.setOfWaitingRequests.add(waitingRequest);
    }

    public Set<WaitingRequest> getSetOfWaitingRequests() {
        return setOfWaitingRequests;
    }

    public Set<Order> getSetOfOrders() {
        return setOfOrders;
    }

    public void addOrder(Order order) {
        this.setOfOrders.add(order);
    }

    public Set<EnrolledSection> getSetOfEnrolledSections() {
        return setOfEnrolledSections;
    }

    public void addEnrolledSection(EnrolledSection enrolledSection) {
        this.setOfEnrolledSections.add(enrolledSection);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<WaiverRequest> getSetOfWaiverRequests() {
        return setOfWaiverRequests;
    }

    public void addWaiverRequest(WaiverRequest waiverRequest) {
        this.setOfWaiverRequests.add(waiverRequest);
    }

    public Faculty getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Faculty advisor) {
        this.advisor = advisor;
        this.advisor.addCustomer(this);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", address=" + address + ", signUpDate=" + signUpDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        hash = 31 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 31 * hash + (this.signUpDate != null ? this.signUpDate.hashCode() : 0);
        hash = 31 * hash + (this.advisor != null ? this.advisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.address == null) ? (other.address != null) : !this.address.equals(other.address)) {
            return false;
        }
        if ((this.signUpDate == null) ? (other.signUpDate != null) : !this.signUpDate.equals(other.signUpDate)) {
            return false;
        }
        if (this.advisor != other.advisor && (this.advisor == null || !this.advisor.equals(other.advisor))) {
            return false;
        }
        return true;
    }

}
