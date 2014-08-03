package com.app.studio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
