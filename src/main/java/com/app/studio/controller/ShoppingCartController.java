package com.app.studio.controller;

import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Customer;
import com.app.studio.model.Product;
import com.app.studio.model.ShoppingCart;
import com.app.studio.model.ShoppingCartItem;
import com.app.studio.service.CustomerService;
import com.app.studio.service.ProductService;
import com.app.studio.service.ShoppingCartService;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ahmadreza
 */
@Controller
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private CustomerService customerService;
    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "shoppingCartService")
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/add-to-shoppingcart/{productID}", method = RequestMethod.GET)
    public String addToShoppingCart(@PathVariable("productID") int productID, Principal user, Model model) {
        Customer customer = customerService.getCustomerByUsername(user.getName());
        Product product = productService.getProductByID(productID);
        try {
            this.shoppingCartService.addToShoppinCart(customer, product);
            model.addAttribute("msg", product.getName() + " successfully added to your cart");
        } catch (RequiredDataNotPresent ex) {
            model.addAttribute("error", ex.getMessage());
        }
        model.addAttribute("listProducts", this.productService.listOfAllProducts());
        return "view-products";
    }

    @RequestMapping(value = "/view-shoppingcart", method = RequestMethod.GET)
    public String listProducts(Product product, Model model, Principal user) {
        Customer customer = customerService.getCustomerByUsername(user.getName());
        model.addAttribute("listShoppingCarts", this.shoppingCartService.listOfAllShoppingCartItems(customer));
        model.addAttribute("username", this.customerService.getCustomerByUsername(user.getName()).getUser());
        model.addAttribute("calculatePrice", this.shoppingCartService.calculatePrice(customer));
        return "view-shoppingcart";
    }

}
