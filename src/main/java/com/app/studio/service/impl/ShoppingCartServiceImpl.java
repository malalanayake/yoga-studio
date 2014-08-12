package com.app.studio.service.impl;

import com.app.studio.dao.CustomerDAO;
import com.app.studio.dao.ProductDAO;
import com.app.studio.dao.ShoppingCartDAO;
import com.app.studio.dao.ShoppingCartItemDAO;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.Product;
import com.app.studio.model.ShoppingCart;
import com.app.studio.model.ShoppingCartItem;
import com.app.studio.service.ShoppingCartService;
import java.util.Iterator;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aTabibi
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartDAO shoppingCartDAO;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private ShoppingCartItemDAO shoppingCartItemDAO;

    public ShoppingCartItemDAO getShoppingCartItemDAO() {
        return shoppingCartItemDAO;
    }

    public void setShoppingCartItemDAO(ShoppingCartItemDAO shoppingCartItemDAO) {
        this.shoppingCartItemDAO = shoppingCartItemDAO;
    }

    public ShoppingCartDAO getShoppingCartDAO() {
        return shoppingCartDAO;
    }

    public void setShoppingCartDAO(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public ShoppingCart addToShoppinCart(Customer customer, Product product) throws RequiredDataNotPresent {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        ShoppingCart shoppingCart = null;
        boolean check = false;
        if (product.getId() != 0 && customer.getId() != 0) {
            if (customer.getShoppingCart() != null) {
                shoppingCart = customer.getShoppingCart();
                Set<ShoppingCartItem> list = shoppingCart.getSetOfShoppingCartItems();
                for (ShoppingCartItem shoppingCartItem1 : list) {
                    if (shoppingCartItem1.getProduct().getId() == product.getId()) {
                        shoppingCartItem1.setQuantity((shoppingCartItem1.getQuantity()) + 1);
                        shoppingCartItemDAO.update(shoppingCartItem1);
                        check = true;
                        break;
                    }
                }
                if (check == false) {
                    shoppingCartItem.setProduct(product);
                    shoppingCartItem.setQuantity(1);
                    shoppingCartItem.setShoppingCart(shoppingCart);
                    shoppingCartItem = shoppingCartItemDAO.create(shoppingCartItem);
                }
            } else if (customer.getShoppingCart() == null) {
                shoppingCart = new ShoppingCart(customer);
                shoppingCart = shoppingCartDAO.create(shoppingCart);
                shoppingCartItem.setProduct(product);
                shoppingCartItem.setQuantity(1);
                shoppingCartItem.setShoppingCart(shoppingCart);
                shoppingCartItem = shoppingCartItemDAO.create(shoppingCartItem);
            }

        } else {
            throw new RequiredDataNotPresent("Required Data Not Present To Customer");
        }
        return shoppingCart;
    }

    @Override
    @Transactional
    public ShoppingCart createNewShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartDAO.create(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void removeShoppingCartItem(int id) {
        this.shoppingCartItemDAO.remove(id);
    }

    @Override
    @Transactional
    public Set<ShoppingCartItem> listOfAllShoppingCartItems(Customer customer) {
        return customer.getShoppingCart().getSetOfShoppingCartItems();
    }

    @Override
    @Transactional
    public int calculatePrice(Customer customer) {
        int sum = 0;
        Set<ShoppingCartItem> list = customer.getShoppingCart().getSetOfShoppingCartItems();
        for (ShoppingCartItem shoppingCartItem1 : list) {
            for (int i = 0; i < shoppingCartItem1.getQuantity(); i++) {
                sum += shoppingCartItem1.getProduct().getPrice();
            }
        }
        return sum;
    }

    @Override
    @Transactional
    public void removeAllShoppingCartItem(ShoppingCart shoppingCart) {
        Set<ShoppingCartItem> list = shoppingCart.getSetOfShoppingCartItems();
        Iterator<ShoppingCartItem> temp = list.iterator();
        while(temp.hasNext()) {
            int index = temp.next().getId();
            this.shoppingCartItemDAO.remove(index);
        }
    }
}
