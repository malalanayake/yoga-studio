package com.app.studio.service;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.Product;
import com.app.studio.model.ShoppingCart;
import com.app.studio.model.ShoppingCartItem;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ahmadreza
 */
public interface ShoppingCartService {

    public ShoppingCart addToShoppinCart(Customer customer, Product product) throws RequiredDataNotPresent;

    public ShoppingCart createNewShoppingCart(ShoppingCart shoppingCart);

    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    public ShoppingCart remove(int id);

    public Set<ShoppingCartItem> listOfAllShoppingCartItems(Customer customer);

    public int calculatePrice(Customer customer);

}
