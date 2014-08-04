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
 * Entity which is going to hold the Waiting Request for the waiting list
 *
 * @author jCalles
 */
@Entity
@Table(name = "WAITING_REQUEST")
public class WaitingRequest {
    
    public WaitingRequest(Section section, Customer customer)
    {
        this.section=section;
        this.section.addToWaitingList(this);
        this.customer=customer;
        this.customer.addWaitingRequest(this);
    }
    

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Section section;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
        this.section.addToWaitingList(this);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WaitingRequest{" + "id=" + id + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 97 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 97 * hash + (this.section != null ? this.section.hashCode() : 0);
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
        final WaitingRequest other = (WaitingRequest) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.section != other.section && (this.section == null || !this.section.equals(other.section))) {
            return false;
        }
        return true;
    }

}
