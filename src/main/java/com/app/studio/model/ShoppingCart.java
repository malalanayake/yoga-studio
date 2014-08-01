package com.app.studio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity which is going to hold the Shopping cart data
 *
 * @author malalanayake
 */
@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "id=" + id + '}';
    }

}
