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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class which holds the order data
 *
 * @author Yen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByOrderStatus", query = "select u from Order u where u.status=:orderStatus")
})
@Table(name = "YOGA_ORDER")
public class Order {

    public static interface Constants {

        public static String STATUS_PROCESSING = "PROCESSING";
        public static String STATUS_COMPLETED = "COMPLETED";

        public static final String NAME_QUERY_FIND_BY_ORDER_STATUS = "findByOrderStatus";
        public static final String PARAM_ORDER_STATUS = "orderStatus";

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private double totalPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderItem> setOfOrderItems;

    public Order() {
        this.setOfOrderItems = new HashSet<OrderItem>();
    }

    public Order(Customer customer) {
        this();
        this.customer = customer;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.setOfOrderItems.add(orderItem);
    }

    public Set<OrderItem> getSetOfOrderItems() {
        return setOfOrderItems;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "" + id + " " + status + "-" + totalPrice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.totalPrice) ^ (Double.doubleToLongBits(this.totalPrice) >>> 32));
        hash = 83 * hash + (this.customer != null ? this.customer.hashCode() : 0);
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
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalPrice) != Double.doubleToLongBits(other.totalPrice)) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }

}
