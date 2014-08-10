package com.app.studio.controller;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Product;
import com.app.studio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dmalalanayake
 */
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String requestToListOfProducts(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", this.productService.listOfAllProducts());
        return "product";
    }
    
    @RequestMapping(value = "/view-products", method = RequestMethod.GET)
    public String listProducts2(Model model) {
        model.addAttribute("listProducts", this.productService.listOfAllProducts());
        return "view-products";
    }
    
    @RequestMapping(value = "/products/edit/{id}")
    public String requestToUpdateProduct(@PathVariable("id") int id, Model model) {
        Product pro = new Product();
        pro = productService.getProductByID(id);
        model.addAttribute("product", pro);
        model.addAttribute("listProducts", this.productService.listOfAllProducts());
        return "product";
    }
    

    @RequestMapping(value = "/products/remove/{id}")
    public String requestToRemoveProduct(@PathVariable("id") int id) {
            this.productService.deleteProduct(id);
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/add")
    public String requestToAddNewProduct(@ModelAttribute("product") Product c) {
        String error = "";
        try {
            if (c.getId() == 0) {

                this.productService.createNewProduct(c);

            } else {
                this.productService.updateProduct(c);
            }
        } catch (RequiredDataNotPresent ex) {
            error = ex.toString();
        } catch (RecordAlreadyExistException ex) {
            error = ex.toString();
        }

        return "redirect:/products";

    }
}
